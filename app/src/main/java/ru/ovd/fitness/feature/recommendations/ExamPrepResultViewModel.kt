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

    /**
     * Генерирует программу на основе данных из предыдущего экрана.
     * Данные передаются через общий ViewModel — пока упрощённо, через prefs + selected exercises
     * нужно передавать через навигацию (следующий шаг).
     */
    fun generate(
        age: Int,
        level: String,
        daysLeft: Int,
        targetPoints: Int,
        exercises: List<SelectedExercise>
    ) {
        _uiState.value = ExamPrepResultUiState(isLoading = true)

        viewModelScope.launch {
            try {
                // Загружаем баллы по каждому упражнению
                val scoresMap = mutableMapOf<Int, List<ExerciseScore>>()
                for (ex in exercises) {
                    val scores = repo.getExerciseScores(prefs.getGender(), ex.orderNumber)
                    val filtered = scores.filter {
                        it.resultMinSec != null || it.resultMaxSec != null
                    }
                    scoresMap[ex.orderNumber] = filtered
                }

                val result = ExamPrepEngine.generate(
                    age = age,
                    level = level,
                    daysLeft = daysLeft,
                    totalTargetPoints = targetPoints,
                    exercises = exercises,
                    scoreLists = scoresMap
                )

                _uiState.value = ExamPrepResultUiState(
                    isLoading = false,
                    result = result
                )
            } catch (e: Exception) {
                _uiState.value = ExamPrepResultUiState(
                    isLoading = false,
                    error = "Ошибка: ${e.message}"
                )
            }
        }
    }
}
