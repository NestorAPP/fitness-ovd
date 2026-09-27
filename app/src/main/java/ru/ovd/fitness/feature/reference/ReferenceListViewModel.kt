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

data class ReferenceListUiState(
    val isLoading: Boolean = true,
    val gender: String = "male",
    val exercises: List<Exercise> = emptyList()
)

class ReferenceListViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = FitnessRepository(application)
    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(ReferenceListUiState())
    val uiState: StateFlow<ReferenceListUiState> = _uiState

    init {
        // Загружаем данные сразу при создании ViewModel
        load()
    }

    fun load() {
        val gender = prefs.getGender()

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
                    gender = gender,
                    exercises = emptyList()
                )
            }
        }
    }
}
