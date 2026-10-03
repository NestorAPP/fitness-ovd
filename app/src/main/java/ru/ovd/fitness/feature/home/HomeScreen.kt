package ru.ovd.fitness.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite
import ru.ovd.fitness.feature.combat.CombatScreen
import ru.ovd.fitness.feature.fitness.FitnessNavHost
import ru.ovd.fitness.feature.recommendations.RecommendationsNavHost
import ru.ovd.fitness.feature.reference.ReferenceNavHost
import ru.ovd.fitness.feature.settings.SettingsNavHost

data class TabItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    val tabs = listOf(
        TabItem("tab_fitness",         "Итоговый бал",  Icons.Default.FitnessCenter),
        TabItem("tab_reference",       "Справочник",    Icons.Default.MenuBook),
        TabItem("tab_recommendations", "Рекомендации",  Icons.Default.TipsAndUpdates),
        TabItem("tab_combat",          "БПБ",           Icons.Default.Security)
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Показываем таб-бар только на главных вкладках
    val showBottomBar = currentRoute in tabs.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 4.dp,
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                ) {
                    tabs.forEach { tab ->
                        val selected = currentRoute == tab.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navController.navigate(tab.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    if (selected) {
                                        Row(
                                            modifier = Modifier
                                                .width(24.dp)
                                                .height(3.dp)
                                        ) {
                                            Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriWhite))
                                            Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriBlue))
                                            Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriRed))
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                    } else {
                                        Spacer(modifier = Modifier.height(7.dp))
                                    }
                                    Icon(
                                        imageVector = tab.icon,
                                        contentDescription = tab.title
                                    )
                                }
                            },
                            label = { Text(tab.title, maxLines = 1, fontSize = 11.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor   = OvdDarkBlue,
                                selectedTextColor   = OvdDarkBlue,
                                indicatorColor      = OvdDarkBlue.copy(alpha = 0.08f),
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "tab_fitness",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("tab_fitness")         { FitnessNavHost() }
            composable("tab_reference")       {
                ReferenceNavHost(
                    onSettingsClick = { navController.navigate("settings_main") }
                )
            }
            composable("tab_recommendations") { RecommendationsNavHost() }
            composable("tab_combat")          { CombatScreen() }
            composable("settings_main")       { SettingsNavHost() }
        }
    }
}
