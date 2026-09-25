package ru.ovd.fitness.feature.fitness

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ovd.fitness.core.data.CalculationResult
import ru.ovd.fitness.core.data.FitnessCalculator
import ru.ovd.fitness.core.data.QualificationInfo

/**
 * Состояние экрана результата.
 */
data class ResultUiState(
    val isLoading: Boolean = true,
    val error: String? = null,

    val ageGroupLabel: String = "",
    val ageGroupNumber: Int = 0,

    val minPoints: Int = 0,
    val recommendedPoints: Int = 0,

    val qualifications: List<QualificationInfo> = emptyList()
)

/**
 * ViewModel для экрана результата.
 *
 * Получает пол/возраст/уровень, обращается к базе данных,
 * получает минимальные баллы и список званий.
 */
class ResultViewModel(application: Application) : AndroidViewModel(application) {

    private val calculator = FitnessCalculator(application)

    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState> = _uiState

    /**
     * Запустить расчёт.
     */
    fun calculate(gender: String, age: Int, level: String) {
        _uiState.value = ResultUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val result = calculator.calculate(gender, age, level)

                _uiState.value = when (result) {
                    is CalculationResult.Success -> ResultUiState(
                        isLoading = false,
                        ageGroupLabel = result.ageGroupLabel,
                        ageGroupNumber = result.ageGroupNumber,
                        minPoints = result.minPoints,
                        recommendedPoints = result.recommendedPoints,
                        qualifications = result.qualifications
                    )

                    is CalculationResult.Error -> ResultUiState(
                        isLoading = false,
                        error = result.message
                    )
                }
            } catch (e: Exception) {
                _uiState.value = ResultUiState(
                    isLoading = false,
                    error = "Ошибка расчёта: ${e.message}"
                )
            }
        }
    }
}
