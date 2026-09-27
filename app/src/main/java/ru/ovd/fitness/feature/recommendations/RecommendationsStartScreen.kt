package ru.ovd.fitness.feature.recommendations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.data.UserPreferences

/**
 * Точка входа в раздел «Рекомендации».
 *
 * Проверяет, принял ли пользователь дисклеймер.
 * Если нет — ведёт на экран дисклеймера.
 * Если да — ведёт на экран ввода данных.
 */
@Composable
fun RecommendationsStartScreen(navController: NavHostController) {

    val context = LocalContext.current
    val prefs = UserPreferences(context)

    LaunchedEffect(Unit) {
        val accepted = prefs.isRecommendationsDisclaimerAccepted()

        if (accepted) {
            navController.navigate("recommendations_input") {
                popUpTo("recommendations_start") { inclusive = true }
            }
        } else {
            navController.navigate("recommendations_disclaimer") {
                popUpTo("recommendations_start") { inclusive = true }
            }
        }
    }

    // Пустой экран — пользователь его не увидит
    Box(modifier = Modifier.fillMaxSize())
}
