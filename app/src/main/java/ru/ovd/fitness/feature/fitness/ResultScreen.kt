package ru.ovd.fitness.feature.fitness

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextPrimary
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite

@Composable
fun ResultScreen(
    navController: NavHostController,
    gender: String,
    age: Int,
    level: String,
    viewModel: ResultViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // ─── Запуск расчёта с реальными данными ───
    LaunchedEffect(gender, age, level) {
        viewModel.calculate(gender = gender, age = age, level = level)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Твои нормативы",
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

        // ─── Введённые данные (для проверки) ───
        Text(
            text = formatInput(gender, age, level),
            color = TextSecondary,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator(color = OvdDarkBlue)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Считаем...",
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            }

            state.error != null -> {
                Text(
                    text = state.error ?: "",
                    color = TriRed,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }

            else -> {
                InfoCard(
                    title = "Возрастная группа",
                    value = "Группа ${state.ageGroupNumber} · ${state.ageGroupLabel}",
                    valueColor = OvdDarkBlue
                )

                Spacer(modifier = Modifier.height(16.dp))

                ScoreCard(
                    label = "Минимум для сдачи",
                    value = state.minPoints.toString(),
                    subtitle = "Рекомендуем набрать ${state.recommendedPoints}",
                    isMain = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Квалификационные звания",
                    style = MaterialTheme.typography.titleMedium,
                    color = OvdDarkBlue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                state.qualifications.forEach { q ->
                    QualificationRow(name = q.name, points = q.minPoints)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

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
                text = "←  Изменить данные",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ─── Форматирование «введённых данных» ───
private fun formatInput(gender: String, age: Int, level: String): String {
    val g = when (gender) {
        "male" -> "Мужской"
        "female" -> "Женский"
        else -> gender
    }
    val l = when (level) {
        "base" -> "Базовый"
        "enhanced" -> "Усиленный"
        "special" -> "Специальный"
        else -> level
    }
    return "$g · $age лет · $l"
}

@Composable
private fun InfoCard(title: String, value: String, valueColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceWhite)
            .padding(16.dp)
    ) {
        Text(text = title, color = TextSecondary, fontSize = 13.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = valueColor, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}

@Composable
private fun ScoreCard(
    label: String,
    value: String,
    subtitle: String,
    isMain: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(if (isMain) OvdDarkBlue else SurfaceWhite)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = if (isMain) TriWhite.copy(alpha = 0.8f) else TextSecondary,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            color = if (isMain) TriWhite else OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 56.sp
        )
        Text(
            text = "баллов",
            color = if (isMain) TriWhite.copy(alpha = 0.8f) else TextSecondary,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subtitle,
            color = if (isMain) TriWhite.copy(alpha = 0.9f) else TextSecondary,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun QualificationRow(name: String, points: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceWhite)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            color = TextPrimary,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$points",
            color = OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "б.", color = TextSecondary, fontSize = 13.sp)
    }
}
