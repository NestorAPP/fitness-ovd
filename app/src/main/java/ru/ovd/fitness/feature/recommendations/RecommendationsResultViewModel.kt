package ru.ovd.fitness.feature.recommendations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.ovd.fitness.core.data.UserPreferences

/**
 * Состояние экрана рекомендаций.
 */
data class RecommendationsResultUiState(
    val isLoading: Boolean = true,
    val result: RecommendationResult? = null
)

class RecommendationsResultViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = UserPreferences(application)

    private val _uiState = MutableStateFlow(RecommendationsResultUiState())
    val uiState: StateFlow<RecommendationsResultUiState> = _uiState

    /**
     * Сформировать рекомендации на основе сохранённых данных.
     */
    fun generate() {
        _uiState.value = RecommendationsResultUiState(isLoading = true)

        try {
            val result = RecommendationEngine.generate(
                gender = prefs.getGender(),
                age = prefs.getAge(),
                heightCm = prefs.getHeight(),
                weightKg = prefs.getWeight(),
                activityLevel = prefs.getActivityLevel()
            )

            _uiState.value = RecommendationsResultUiState(
                isLoading = false,
                result = result
            )
        } catch (e: Exception) {
            _uiState.value = RecommendationsResultUiState(isLoading = false, result = null)
        }
    }
}
