package ru.ovd.fitness.feature.recommendations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.ovd.fitness.core.data.UserPreferences

/**
 * Состояние экрана ввода данных для рекомендаций.
 */
data class RecommendationsInputUiState(
    val gender: String = "male",
    val age: Int = 30,
    val height: Int = 175,
    val weight: Int = 75,
    val activityLevel: Int = 3
) {
    val isValid: Boolean
        get() = age in 18..70
                && height in 140..220
                && weight in 40..200
                && activityLevel in 1..5
}

class RecommendationsInputViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(
        RecommendationsInputUiState(
            gender = prefs.getGender(),
            age = prefs.getAge(),
            height = prefs.getHeight(),
            weight = prefs.getWeight(),
            activityLevel = prefs.getActivityLevel()
        )
    )
    val uiState: StateFlow<RecommendationsInputUiState> = _uiState

    fun setGender(gender: String) {
        _uiState.update { it.copy(gender = gender) }
        prefs.setGender(gender)
    }

    fun setAge(age: Int) {
        val safe = age.coerceIn(18, 70)
        _uiState.update { it.copy(age = safe) }
        prefs.setAge(safe)
    }

    fun setHeight(height: Int) {
        val safe = height.coerceIn(140, 220)
        _uiState.update { it.copy(height = safe) }
        prefs.setHeight(safe)
    }

    fun setWeight(weight: Int) {
        val safe = weight.coerceIn(40, 200)
        _uiState.update { it.copy(weight = safe) }
        prefs.setWeight(safe)
    }

    fun setActivityLevel(level: Int) {
        val safe = level.coerceIn(1, 5)
        _uiState.update { it.copy(activityLevel = safe) }
        prefs.setActivityLevel(safe)
    }
}
