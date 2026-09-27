package ru.ovd.fitness.feature.reference

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ovd.fitness.core.data.FitnessRepository
import ru.ovd.fitness.core.data.ScoreFiller
import ru.ovd.fitness.core.data.UserPreferences
import ru.ovd.fitness.core.data.entity.Exercise
import ru.ovd.fitness.core.data.entity.ExerciseScore

data class ReferenceDetailUiState(
    val isLoading: Boolean = true,
    val exerciseName: String = "",
    val exerciseUnit: String = "",
    val scores: List<ExerciseScore> = emptyList(),
    val selectedIndex: Int = 0,
    val error: String? = null
) {
    val currentScore: ExerciseScore?
        get() = scores.getOrNull(selectedIndex)
}

class ReferenceDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = FitnessRepository(application)
    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(ReferenceDetailUiState())
    val uiState: StateFlow<ReferenceDetailUiState> = _uiState

    fun load(orderNumber: Int) {
        val gender = prefs.getGender()
        if (gender == null) {
            _uiState.value = ReferenceDetailUiState(
                isLoading = false,
                error = "Выберите пол в разделе «Физо»"
            )
            return
        }

        _uiState.value = ReferenceDetailUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val exercises = repo.getExercises(gender)
                val exercise = exercises.find { it.orderNumber == orderNumber }

                if (exercise == null) {
                    _uiState.value = ReferenceDetailUiState(
                        isLoading = false,
                        error = "Упражнение не найдено"
                    )
                    return@launch
                }

                val rawScores = repo.getExerciseScores(gender, orderNumber)
                val filledScores = ScoreFiller.fill(rawScores)

                _uiState.value = ReferenceDetailUiState(
                    isLoading = false,
                    exerciseName = exercise.name,
                    exerciseUnit = exercise.unit,
                    scores = filledScores,
                    selectedIndex = 0
                )
            } catch (e: Exception) {
                _uiState.value = ReferenceDetailUiState(
                    isLoading = false,
                    error = "Ошибка загрузки: ${e.message}"
                )
            }
        }
    }

    fun selectIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedIndex = index)
    }
}
