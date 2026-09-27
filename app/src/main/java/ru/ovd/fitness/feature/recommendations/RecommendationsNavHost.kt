package ru.ovd.fitness.feature.recommendations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.ovd.fitness.core.ui.navigation.NavDepthHolder

@Composable
fun RecommendationsNavHost(
    depthHolder: NavDepthHolder,
    navController: NavHostController = rememberNavController()
) {
    // Следим за стеком навигации
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        // «В глубине» — всё, что НЕ стартовый экран
        val isDeep = currentRoute != null && currentRoute != "recommendations_start"
        depthHolder.setDepth("recommendations", isDeep)
    }

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
