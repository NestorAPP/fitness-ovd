package ru.ovd.fitness.feature.fitness

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * Состояние экрана ввода.
 *
 * Хранит то, что ввёл сотрудник: пол, возраст, уровень подготовки.
 */
data class InputUiState(
    /** Пол: "male" / "female" */
    val gender: String = "male",

    /** Возраст: 18..70 */
    val age: Int = 30,

    /** Уровень: "base" / "enhanced" / "special" */
    val level: String = "base"
) {
    /** Можно ли нажать кнопку «Рассчитать» */
    val isValid: Boolean
        get() = age in 18..70
}

/**
 * ViewModel для экрана ввода.
 *
 * Отвечает за хранение введённых данных.
 */
class InputViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InputUiState())
    val uiState: StateFlow<InputUiState> = _uiState

    /** Установить пол */
    fun setGender(gender: String) {
        _uiState.update { it.copy(gender = gender) }
    }

    /** Установить возраст */
    fun setAge(age: Int) {
        _uiState.update { it.copy(age = age.coerceIn(18, 70)) }
    }

    /** Установить уровень */
    fun setLevel(level: String) {
        _uiState.update { it.copy(level = level) }
    }
}
