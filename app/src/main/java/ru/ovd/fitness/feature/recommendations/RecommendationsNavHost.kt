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
        // ─── Стартовый экран с двумя кнопками ───
        composable("recommendations_start") {
            RecommendationsStartScreen(navController = navController)
        }

        // ─── Дисклеймер (для обоих подразделов) ───
        composable("recommendations_disclaimer") {
            RecommendationsDisclaimerScreen(navController = navController)
        }

        // ─── Подраздел 1: Поддержание физической формы ───
        composable("recommendations_input") {
            RecommendationsInputScreen(navController = navController)
        }
        composable("recommendations_maintenance") {
            RecommendationsScreen(navController = navController)
        }

        // ─── Подраздел 2: Подготовка к итоговым занятиям ───
        composable("exam_prep_input") {
            ExamPrepInputScreen(navController = navController)
        }
        composable("exam_prep_result") {
            ExamPrepResultScreen(navController = navController)
        }
    }
}
