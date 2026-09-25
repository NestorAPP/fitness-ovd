package ru.ovd.fitness.feature.fitness

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun FitnessNavHost(
    navController: NavHostController = rememberNavController()
) {
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
                navArgument("gender") {
                    type = NavType.StringType
                    defaultValue = "male"
                },
                navArgument("age") {
                    type = NavType.IntType
                    defaultValue = 30
                },
                navArgument("level") {
                    type = NavType.StringType
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
