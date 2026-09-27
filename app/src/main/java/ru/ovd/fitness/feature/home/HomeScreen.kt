package ru.ovd.fitness.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite
import ru.ovd.fitness.feature.fitness.FitnessNavHost
import ru.ovd.fitness.feature.recommendations.RecommendationsNavHost
import ru.ovd.fitness.feature.reference.ReferenceNavHost

data class TabItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()

    val tabs = listOf(
        TabItem("Итоговый бал",  Icons.Default.FitnessCenter),
        TabItem("Справочник",    Icons.Default.MenuBook),
        TabItem("Рекомендации",  Icons.Default.TipsAndUpdates)
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )

    // Следим за сменой страницы — чтобы при свайпе таб-бар подсвечивался
    val currentPage = pagerState.currentPage

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 4.dp,
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                tabs.forEachIndexed { index, tab ->
                    val selected = currentPage == index
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            // Тап на таб — перелистываем Pager
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (selected) {
                                    Row(
                                        modifier = Modifier
                                            .width(28.dp)
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
                        label = { Text(tab.title, maxLines = 1) },
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
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) { page ->
            when (page) {
                0 -> FitnessNavHost()
                1 -> ReferenceNavHost()
                2 -> RecommendationsNavHost()
            }
        }
    }
}
