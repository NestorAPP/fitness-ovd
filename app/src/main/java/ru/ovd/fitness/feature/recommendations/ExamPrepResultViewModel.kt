package ru.ovd.fitness.feature.recommendations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ovd.fitness.core.data.FitnessRepository
import ru.ovd.fitness.core.data.UserPreferences
import ru.ovd.fitness.core.data.entity.ExerciseScore

data class ExamPrepResultUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val result: ExamPrepResult? = null
)

class ExamPrepResultViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = FitnessRepository(application)
    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(ExamPrepResultUiState())
    val uiState: StateFlow<ExamPrepResultUiState> = _uiState

    fun generate() {
        _uiState.value = ExamPrepResultUiState(isLoading = true)

        viewModelScope.launch {
            try {
                // ─── Читаем данные из prefs ───
                val age = prefs.getAge()
                val level = prefs.getLevel()
                val examDate = prefs.getExamDate()

                val daysLeft = if (examDate > 0) {
                    ((examDate - System.currentTimeMillis()) / (1000 * 60 * 60 * 24)).toInt()
                } else 0

                // ─── Парсим упражнения ───
                val serialized = prefs.getSelectedExercises()
                val exercises = parseExercises(serialized)

                if (exercises.isEmpty() || daysLeft <= 0) {
                    _uiState.value = ExamPrepResultUiState(
                        isLoading = false,
                        error = "Недостаточно данных для построения программы"
                    )
                    return@launch
                }

                // ─── Целевые баллы ───
                val targetPoints = calculateTargetPoints(
                    gender = prefs.getGender(),
                    level = level,
                    age = age,
                    examType = prefs.getExamType(),
                    qualificationName = prefs.getQualificationName()
                )

                // ─── Загружаем баллы упражнений ───
                val scoresMap = mutableMapOf<Int, List<ExerciseScore>>()
                for (ex in exercises) {
                    val scores = repo.getExerciseScores(prefs.getGender(), ex.orderNumber)
                    val filtered = scores.filter {
                        it.resultMinSec != null || it.resultMaxSec != null
                    }
                    scoresMap[ex.orderNumber] = filtered
                }

                // ─── Генерируем программу ───
                val result = ExamPrepEngine.generate(
                    age = age,
                    level = level,
                    daysLeft = daysLeft,
                    totalTargetPoints = targetPoints,
                    exercises = exercises,
                    scoreLists = scoresMap
                )

                _uiState.value = ExamPrepResultUiState(isLoading = false, result = result)

            } catch (e: Exception) {
                _uiState.value = ExamPrepResultUiState(
                    isLoading = false,
                    error = "Ошибка: ${e.message}"
                )
            }
        }
    }

    /**
     * Парсит строку упражнений из prefs.
     * Формат: "orderNumber|name|category|unit|currentResult||orderNumber|..."
     */
    private fun parseExercises(raw: String): List<SelectedExercise> {
        if (raw.isBlank()) return emptyList()
        return raw.split("||").mapNotNull { part ->
            val fields = part.split("|")
            if (fields.size < 5) return@mapNotNull null
            SelectedExercise(
                orderNumber = fields[0].toIntOrNull() ?: return@mapNotNull null,
                name = fields[1],
                category = fields[2],
                unit = fields[3],
                currentResult = fields[4]
            )
        }
    }

    /**
     * Считает целевой балл.
     * Для итоговых — минимум для сдачи по группе.
     * Для звания — минимум по выбранному званию.
     */
    private suspend fun calculateTargetPoints(
        gender: String,
        level: String,
        age: Int,
        examType: String,
        qualificationName: String?
    ): Int {
        val levelKey = when (level) {
            "base" -> "base"
            "enhanced" -> "enhanced"
            else -> "special"
        }

        val ageGroup = repo.getAgeGroup(gender, age) ?: return 50

        if (examType == "QUALIFICATION" && qualificationName != null) {
            val quals = repo.getQualifications(gender, ageGroup.groupNumber, levelKey)
            val q = quals.find { it.qualificationName == qualificationName }
            return q?.minPoints ?: 50
        }

        // Итоговые занятия — минимум
        val pass = repo.getPassingScore(gender, ageGroup.groupNumber, levelKey)
        return pass?.minPoints ?: 50
    }
}
