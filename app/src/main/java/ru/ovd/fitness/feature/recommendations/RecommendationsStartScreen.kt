package ru.ovd.fitness.feature.recommendations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextPrimary
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.feature.fitness.HeaderBlock

@Composable
fun RecommendationsStartScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ─── ШАПКА ───
        HeaderBlock(title = "Рекомендации")

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ─── Кнопка 1: Поддержание формы ───
            MenuCard(
                title = "Поддержание физической формы",
                subtitle = "Программа для общего тонуса",
                emoji = "💪",
                onClick = {
                    navController.navigate("recommendations_maintenance")
                }
            )

            // ─── Кнопка 2: Подготовка к итоговым ───
            MenuCard(
                title = "Подготовка к итоговым занятиям",
                subtitle = "Программа для сдачи нормативов",
                emoji = "🎯",
                onClick = {
                    navController.navigate("exam_prep_input")
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun MenuCard(
    title: String,
    subtitle: String,
    emoji: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(OvdLightBlue)
            .clickable(onClick = onClick)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = emoji,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = OvdDarkBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                color = TextSecondary,
                fontSize = 13.sp
            )
        }

        Text(
            text = "→",
            color = OvdDarkBlue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
