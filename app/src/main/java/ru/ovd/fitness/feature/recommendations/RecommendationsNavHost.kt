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
        composable("recommendations_start") {
            RecommendationsStartScreen(navController = navController)
        }

        composable("recommendations_disclaimer") {
            RecommendationsDisclaimerScreen(navController = navController)
        }

        composable("recommendations_input") {
            RecommendationsInputScreen(navController = navController)
        }

        composable("recommendations_result") {
            RecommendationsScreen(navController = navController)
        }
    }
}
