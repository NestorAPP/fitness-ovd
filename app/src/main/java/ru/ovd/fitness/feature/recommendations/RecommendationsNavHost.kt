package ru.ovd.fitness.feature.recommendations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RecommendationsNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "recommendations_start"
    ) {
        // Точка входа — здесь решаем, что показать: дисклеймер или ввод данных
        composable("recommendations_start") {
            RecommendationsStartScreen(navController = navController)
        }

        // Экран дисклеймера
        composable("recommendations_disclaimer") {
            RecommendationsDisclaimerScreen(navController = navController)
        }

        // Экран ввода данных
        composable("recommendations_input") {
            RecommendationsInputScreen(navController = navController)
        }

        // Экран рекомендаций (пока заглушка)
        composable("recommendations_result") {
            RecommendationsScreen(navController = navController)
        }
    }
}
