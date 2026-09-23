package ru.ovd.fitness.feature.fitness

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FitnessNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "fitness_input"
        // Анимации переходов убраны — переключение мгновенное.
    ) {
        composable("fitness_input") {
            InputScreen(navController = navController)
        }
        composable("fitness_result") {
            ResultScreen(navController = navController)
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
