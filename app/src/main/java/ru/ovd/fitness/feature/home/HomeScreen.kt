package ru.ovd.fitness.feature.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.feature.fitness.FitnessNavHost
import ru.ovd.fitness.feature.shooting.ShootingScreen
import ru.ovd.fitness.feature.video.VideoScreen

data class TabItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    val tabs = listOf(
        TabItem("tab_fitness",  "Физо",    Icons.Default.FitnessCenter),
        TabItem("tab_shooting", "Огневая", Icons.Default.MyLocation),
        TabItem("tab_video",    "Видео",   Icons.Default.PlayCircle)
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
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
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title
                            )
                        },
                        label = { Text(tab.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor   = OvdDarkBlue,
                            selectedTextColor   = OvdDarkBlue,
                            indicatorColor      = OvdDarkBlue.copy(alpha = 0.12f),
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        )
                    )
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

            // ─── Между вкладками таб-бара — БЕЗ анимаций ───
            // Это устраняет наложение старого экрана на новый.
            // Переключение мгновенное, как в Telegram.
        ) {
            composable("tab_fitness")  { FitnessNavHost() }
            composable("tab_shooting") { ShootingScreen() }
            composable("tab_video")    { VideoScreen() }
        }
    }
}
