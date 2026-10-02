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
import ru.ovd.fitness.core.ui.theme.TriWhite
import ru.ovd.fitness.feature.fitness.HeaderBlock

@Composable
fun ExamPrepResultScreen(
    navController: NavHostController,
    viewModel: ExamPrepResultViewModel = viewModel()
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
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderBlock(title = "Программа подготовки")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 24.dp)
        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(300.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = OvdDarkBlue)
                    }
                }

                state.error != null -> {
                    Text(
                        text = state.error ?: "",
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                state.result != null -> {
                    val result = state.result!!

                    // ─── Сроки ───
                    Text(
                        text = "До сдачи: ${result.daysLeft} дней",
                        color = OvdDarkBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // ─── Целевой балл ───
                    Text(
                        text = "Цель: набрать ${result.totalTargetPoints} баллов",
                        color = TextSecondary,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // ─── Предупреждение ───
                    result.warning?.let { w ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .background(StatusRed.copy(alpha = 0.1f))
                                .padding(16.dp)
                        ) {
                            Text(text = "⚠ $w", color = StatusRed, fontSize = 14.sp, lineHeight = 20.sp)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // ─── Цели по упражнениям ───
                    Text(
                        text = "Задачи по упражнениям",
                        color = OvdDarkBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    result.exerciseGoals.forEach { goal ->
                        GoalCard(goal)
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // ─── Фазы ───
                    Text(
                        text = "План подготовки",
                        color = OvdDarkBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    result.phases.forEach { phase ->
                        PhaseCard(phase)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth().height(56.dp),
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
}

@Composable
private fun GoalCard(goal: ExerciseGoal) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceWhite)
            .padding(16.dp)
    ) {
        Text(text = goal.exerciseName, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 15.sp, lineHeight = 20.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "${goal.category} · ${goal.unit}", color = TextSecondary, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = "Сейчас", color = TextSecondary, fontSize = 12.sp)
                Text(text = goal.currentResult.ifBlank { "—" }, color = OvdDarkBlue, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "Цель (${goal.targetPoints} б.)", color = TextSecondary, fontSize = 12.sp)
                Text(text = goal.targetResult, color = OvdDarkBlue, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun PhaseCard(phase: PrepPhase) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(OvdLightBlue)
            .padding(16.dp)
    ) {
        Text(text = phase.title, color = OvdDarkBlue, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = phase.subtitle, color = TextSecondary, fontSize = 13.sp)
        Spacer(modifier = Modifier.height(12.dp))

        phase.items.forEach { item ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "• ", color = OvdDarkBlue, fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = 20.sp)
                Text(text = item, color = TextPrimary, fontSize = 14.sp, lineHeight = 20.sp)
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}
