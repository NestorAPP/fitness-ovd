package ru.ovd.fitness.feature.reference

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ReferenceNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "reference_list"
    ) {
        composable("reference_list") {
            ReferenceListScreen(navController = navController)
        }

        composable(
            route = "reference_detail?orderNumber={orderNumber}",
            arguments = listOf(
                navArgument("orderNumber") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) { entry ->
            val orderNumber = entry.arguments?.getInt("orderNumber") ?: 1
            ReferenceDetailScreen(
                navController = navController,
                orderNumber = orderNumber
            )
        }
    }
}
