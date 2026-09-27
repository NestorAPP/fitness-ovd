package ru.ovd.fitness.feature.recommendations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.TextSecondary

@Composable
fun RecommendationsInputScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Экран ввода данных\n(в разработке)",
            style = MaterialTheme.typography.titleLarge,
            color = TextSecondary,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp
        )
    }
}
