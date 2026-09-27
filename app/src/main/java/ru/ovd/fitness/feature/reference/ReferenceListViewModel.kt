package ru.ovd.fitness.feature.reference

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ovd.fitness.core.data.FitnessRepository
import ru.ovd.fitness.core.data.UserPreferences
import ru.ovd.fitness.core.data.entity.Exercise

/**
 * Состояние экрана списка упражнений.
 */
data class ReferenceListUiState(
    val isLoading: Boolean = true,
    val genderNotSelected: Boolean = false,
    val gender: String = "",
    val exercises: List<Exercise> = emptyList()
)

class ReferenceListViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = FitnessRepository(application)
    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(ReferenceListUiState())
    val uiState: StateFlow<ReferenceListUiState> = _uiState

    /** Загрузить список упражнений */
    fun load() {
        val gender = prefs.getGender()

        if (gender == null) {
            _uiState.value = ReferenceListUiState(
                isLoading = false,
                genderNotSelected = true
            )
            return
        }

        _uiState.value = ReferenceListUiState(isLoading = true, gender = gender)

        viewModelScope.launch {
            try {
                val exercises = repo.getExercises(gender)
                _uiState.value = ReferenceListUiState(
                    isLoading = false,
                    gender = gender,
                    exercises = exercises
                )
            } catch (e: Exception) {
                _uiState.value = ReferenceListUiState(
                    isLoading = false,
                    genderNotSelected = false,
                    gender = gender,
                    exercises = emptyList()
                )
            }
        }
    }
}
