package ru.ovd.fitness.feature.fitness

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.ovd.fitness.core.data.UserPreferences

data class InputUiState(
    val gender: String = "male",
    val age: Int = 30,
    val level: String = "base"
) {
    val isValid: Boolean
        get() = age in 18..70
}

class InputViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(
        InputUiState(
            gender = prefs.getGender() ?: "male",
            age = prefs.getAge() ?: 30,
            level = prefs.getLevel() ?: "base"
        )
    )
    val uiState: StateFlow<InputUiState> = _uiState

    fun setGender(gender: String) {
        _uiState.update { it.copy(gender = gender) }
        prefs.setGender(gender)
    }

    fun setAge(age: Int) {
        val safeAge = age.coerceIn(18, 70)
        _uiState.update { it.copy(age = safeAge) }
        prefs.setAge(safeAge)
    }

    fun setLevel(level: String) {
        _uiState.update { it.copy(level = level) }
        prefs.setLevel(level)
    }
}
