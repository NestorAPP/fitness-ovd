package ru.ovd.fitness.feature.fitness

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.ovd.fitness.core.ui.navigation.NavDepthHolder

@Composable
fun FitnessNavHost(
    depthHolder: NavDepthHolder,
    navController: NavHostController = rememberNavController()
) {
    // Следим за стеком навигации — сообщаем depthHolder о глубине
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        val isDeep = currentRoute != null && currentRoute != "fitness_input"
        depthHolder.setDepth("fitness", isDeep)
    }

    NavHost(
        navController = navController,
        startDestination = "fitness_input"
    ) {
        composable("fitness_input") {
            InputScreen(navController = navController)
        }
        composable(
            route = "fitness_result?gender={gender}&age={age}&level={level}",
            arguments = listOf(
                androidx.navigation.navArgument("gender") {
                    type = androidx.navigation.NavType.StringType
                    defaultValue = "male"
                },
                androidx.navigation.navArgument("age") {
                    type = androidx.navigation.NavType.IntType
                    defaultValue = 30
                },
                androidx.navigation.navArgument("level") {
                    type = androidx.navigation.NavType.StringType
                    defaultValue = "base"
                }
            )
        ) { backStackEntry ->
            val gender = backStackEntry.arguments?.getString("gender") ?: "male"
            val age = backStackEntry.arguments?.getInt("age") ?: 30
            val level = backStackEntry.arguments?.getString("level") ?: "base"

            ResultScreen(
                navController = navController,
                gender = gender,
                age = age,
                level = level
            )
        }
        composable("fitness_reference") {
            ReferenceScreen(navController = navController)
        }
        composable("fitness_calculator") {
            CalculatorScreen(navController = navController)
        }
        composable("fitness_recovery") {
            RecoveryScreen(navController = navController)
        }
    }
}
