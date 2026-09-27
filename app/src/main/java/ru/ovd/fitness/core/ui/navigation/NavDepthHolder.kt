package ru.ovd.fitness.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * Держатель состояния «мы внутри подэкрана».
 *
 * Используется для отключения свайпов между вкладками,
 * когда пользователь находится НЕ на главном экране вкладки.
 *
 * Каждая вкладка (Fitness, Reference, Recommendations) сообщает сюда,
 * на «глубине» она или на стартовом экране.
 */
class NavDepthHolder {
    /**
     * Карта: имя вкладки → true (внутри) / false (на главной).
     */
    val depths: MutableState<Map<String, Boolean>> = mutableStateOf(
        mapOf(
            "fitness" to false,
            "reference" to false,
            "recommendations" to false
        )
    )

    /**
     * Установить глубину для вкладки.
     */
    fun setDepth(tab: String, isDeep: Boolean) {
        val current = depths.value.toMutableMap()
        current[tab] = isDeep
        depths.value = current
    }

    /**
     * Есть ли ХОТЯ БЫ ОДНА вкладка «в глубине».
     * Если да — свайп между вкладками отключается.
     */
    fun anyDeep(): Boolean = depths.value.values.any { it }
}

/**
 * Создаёт и запоминает `NavDepthHolder` на уровне композиции.
 */
@Composable
fun rememberNavDepthHolder(): NavDepthHolder {
    return remember { NavDepthHolder() }
}
