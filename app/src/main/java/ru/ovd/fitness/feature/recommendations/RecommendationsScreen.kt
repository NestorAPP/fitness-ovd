package ru.ovd.fitness.feature.recommendations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.StatusRed
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextPrimary
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite
import kotlin.math.roundToInt

@Composable
fun RecommendationsScreen(
    navController: NavHostController,
    viewModel: RecommendationsResultViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.generate()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ─── Заголовок ───
        Text(
            text = "Рекомендации",
            style = MaterialTheme.typography.headlineSmall,
            color = OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .width(80.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriWhite))
            Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriBlue))
            Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriRed))
        }

        Spacer(modifier = Modifier.height(20.dp))

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = OvdDarkBlue)
                }
            }

            state.result == null -> {
                Text(
                    text = "Не удалось сформировать рекомендации.\nВернитесь и заполните данные заново.",
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }

            else -> {
                val result = state.result!!

                // ─── ИМТ ───
                BmiCard(
                    bmi = result.bmi,
                    category = result.bmiCategory.label
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ─── Предупреждение ───
                result.warning?.let { warning ->
                    WarningCard(text = warning)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // ─── Блоки ───
                result.blocks.forEach { block ->
                    RecommendationBlockCard(block = block)
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ─── Кнопка «Изменить параметры» ───
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OvdDarkBlue,
                contentColor = TriWhite
            )
        ) {
            Text(
                text = "←  Изменить параметры",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ═══════════════════════════════════════════════════════
//   КОМПОНЕНТЫ
// ═══════════════════════════════════════════════════════

@Composable
private fun BmiCard(bmi: Double, category: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(SurfaceWhite)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Твой ИМТ",
            color = TextSecondary,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = String.format("%.1f", bmi),
            color = OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category,
            color = OvdDarkBlue,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun WarningCard(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(StatusRed.copy(alpha = 0.1f))
            .padding(16.dp)
    ) {
        Text(
            text = "⚠",
            fontSize = 22.sp,
            color = StatusRed
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            color = StatusRed,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun RecommendationBlockCard(block: RecommendationBlock) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(OvdLightBlue)
            .padding(16.dp)
    ) {
        Text(
            text = block.title,
            color = OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = block.subtitle,
            color = TextSecondary,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        block.items.forEach { item ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "• ",
                    color = OvdDarkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = item,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}
