package ru.ovd.fitness.feature.reference

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.ovd.fitness.core.ui.navigation.NavDepthHolder

@Composable
fun ReferenceNavHost(
    depthHolder: NavDepthHolder,
    navController: NavHostController = rememberNavController()
) {
    // Следим за стеком навигации
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        val isDeep = currentRoute != null && currentRoute != "reference_list"
        depthHolder.setDepth("reference", isDeep)
    }

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
        ) { backStackEntry ->
            val orderNumber = backStackEntry.arguments?.getInt("orderNumber") ?: 1
            ReferenceDetailScreen(
                navController = navController,
                orderNumber = orderNumber
            )
        }
    }
}
