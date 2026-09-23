package ru.ovd.fitness.feature.fitness

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.core.tween
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
        startDestination = "fitness_input",

        // Переходы «вглубь»: новый экран въезжает справа
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 4 },
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 4 },
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        }
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
