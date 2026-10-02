package ru.ovd.fitness.feature.recommendations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.ovd.fitness.core.data.UserPreferences
import java.util.Calendar

/**
 * Одно выбранное упражнение с текущим результатом.
 */
data class SelectedExercise(
    val orderNumber: Int,        // номер упражнения в базе (1..9)
    val name: String,            // название
    val category: String,        // "Сила" / "Быстрота и ловкость" / "Выносливость"
    val unit: String,            // "раз" / "секунд" / "минут, секунд"
    val currentResult: String = "" // текущий результат (пусто — не введён)
)

/**
 * Тип предстоящего испытания.
 */
enum class ExamType(val label: String) {
    FINAL("Итоговые занятия"),
    QUALIFICATION("Испытание на звание")
}

/**
 * Состояние экрана ввода «Подготовка к итоговым».
 */
data class ExamPrepInputUiState(
    val examType: ExamType = ExamType.FINAL,
    val examDateMillis: Long? = null,
    val qualificationName: String? = null,   // для типа QUALIFICATION
    val selectedExercises: List<SelectedExercise> = emptyList(),
    val level: String = "base"
) {
    /** Сколько дней осталось до сдачи */
    val daysLeft: Int?
        get() {
            val millis = examDateMillis ?: return null
            val now = System.currentTimeMillis()
            val diff = millis - now
            return (diff / (1000 * 60 * 60 * 24)).toInt()
        }

    /** Максимум упражнений по уровню */
    val maxExercises: Int
        get() = when (level) {
            "base"     -> 1
            "enhanced" -> 2
            else       -> 3
        }

    /** Можно ли жать «Показать программу» */
    val isValid: Boolean
        get() = examDateMillis != null
                && (daysLeft ?: -1) > 0
                && selectedExercises.isNotEmpty()
                && selectedExercises.size <= maxExercises
                && selectedExercises.all { it.currentResult.isNotBlank() }
}

class ExamPrepInputViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(
        ExamPrepInputUiState(
            level = prefs.getLevel()
        )
    )
    val uiState: StateFlow<ExamPrepInputUiState> = _uiState

    /** Установить тип испытания */
    fun setExamType(type: ExamType) {
        _uiState.update { it.copy(examType = type) }
    }

    /** Установить дату */
    fun setExamDate(millis: Long) {
        _uiState.update { it.copy(examDateMillis = millis) }
    }

    /** Установить звание (для типа QUALIFICATION) */
    fun setQualificationName(name: String?) {
        _uiState.update { it.copy(qualificationName = name) }
    }

    /** Добавить упражнение */
    fun addExercise(exercise: SelectedExercise) {
        _uiState.update { state ->
            if (state.selectedExercises.size >= state.maxExercises) return@update state
            if (state.selectedExercises.any { it.orderNumber == exercise.orderNumber }) return@update state
            state.copy(selectedExercises = state.selectedExercises + exercise)
        }
    }

    /** Удалить упражнение */
    fun removeExercise(orderNumber: Int) {
        _uiState.update { state ->
            state.copy(
                selectedExercises = state.selectedExercises.filter { it.orderNumber != orderNumber }
            )
        }
    }

    /** Обновить текущий результат упражнения */
    fun updateResult(orderNumber: Int, result: String) {
        _uiState.update { state ->
            state.copy(
                selectedExercises = state.selectedExercises.map {
                    if (it.orderNumber == orderNumber) it.copy(currentResult = result) else it
                }
            )
        }
    }
}
