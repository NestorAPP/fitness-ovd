package com.mvd.applicant.ui.training

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mvd.applicant.data.model.Exercise
import com.mvd.applicant.data.model.TrainingDay
import com.mvd.applicant.data.model.TrainingProgram
import com.mvd.applicant.data.model.TrainingWeek
import com.mvd.applicant.ui.theme.MvdBlue
import com.mvd.applicant.ui.theme.MvdRed
import com.mvd.applicant.ui.widgets.DecoratedCard
import com.mvd.applicant.ui.widgets.SectionTitle
import com.mvd.applicant.ui.widgets.SubsectionTitle

@Composable
fun TrainingProgramScreen(
    vm: TrainingViewModel = viewModel(),
    onEditInput: () -> Unit = {}
) {
    val state by vm.state.collectAsState()
    val program = state.program

    if (program == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Программа не рассчитана", color = MvdBlue)
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { SectionTitle("Ваша программа подготовки") }

        item {
            PrioritySummaryCard(program)
        }

        state.warning?.let { warning ->
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MvdRed.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        warning,
                        color = MvdRed,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = state.viewMode == ProgramViewMode.WEEKLY,
                    onClick = { vm.setViewMode(ProgramViewMode.WEEKLY) },
                    label = { Text("Понедельно") }
                )
                FilterChip(
                    selected = state.viewMode == ProgramViewMode.MONTHLY,
                    onClick = { vm.setViewMode(ProgramViewMode.MONTHLY) },
                    label = { Text("Помесячно") }
                )
            }
        }

        when (state.viewMode) {
            ProgramViewMode.WEEKLY -> {
                items(program.weeks) { week ->
                    WeekCard(week)
                }
            }
            ProgramViewMode.MONTHLY -> {
                val grouped = program.weeks.chunked(4)
                items(grouped.size) { idx ->
                    MonthCard(monthNumber = idx + 1, weeks = grouped[idx])
                }
            }
        }

        item {
            Button(
                onClick = onEditInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MvdBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Изменить данные", color = Color.White)
            }
        }

        item { Spacer(Modifier.height(24.dp)) }
    }
}

@Composable
private fun PrioritySummaryCard(program: TrainingProgram) {
    val p = program.priority
    DecoratedCard {
        SubsectionTitle("Текущие баллы и приоритеты")
        Spacer(Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ScorePill("Сила", p.strengthPoints, p.strengthGap)
            ScorePill("100 м", p.speedPoints, p.speedGap)
            ScorePill("1000 м", p.endurancePoints, p.enduranceGap)
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "Акцент программы: ${p.focusAreas.joinToString(", ")}",
            style = MaterialTheme.typography.bodyMedium,
            color = MvdRed
        )
    }
}

@Composable
private fun ScorePill(name: String, points: Int, gap: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(name, style = MaterialTheme.typography.bodyMedium, color = MvdBlue)
        Text("$points", style = MaterialTheme.typography.titleLarge, color = MvdBlue)
        Text("разрыв $gap", style = MaterialTheme.typography.bodySmall, color = MvdRed)
    }
}

@Composable
private fun WeekCard(week: TrainingWeek) {
    DecoratedCard {
        Text(
            "Неделя ${week.weekNumber} — ${week.phase}",
            style = MaterialTheme.typography.titleLarge,
            color = MvdBlue
        )
        if (week.notes.isNotBlank()) {
            Spacer(Modifier.height(4.dp))
            Text(week.notes, style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Spacer(Modifier.height(8.dp))
        week.days.forEach { day ->
            DayBlock(day)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun DayBlock(day: TrainingDay) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F7FA)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp)) {
            Text("${day.dayName}: ${day.focus}",
                style = MaterialTheme.typography.titleMedium, color = MvdBlue)
            Spacer(Modifier.height(6.dp))
            Text("Разминка: ${day.warmup}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(6.dp))
            day.exercises.forEach { ex -> ExerciseRow(ex) }
            Spacer(Modifier.height(6.dp))
            Text("Заминка: ${day.cooldown}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ExerciseRow(ex: Exercise) {
    Column(Modifier.padding(vertical = 3.dp)) {
        Text(
            "• ${ex.name}: ${ex.sets}×${ex.reps}, отдых ${ex.restSeconds} сек",
            style = MaterialTheme.typography.bodyMedium,
            color = MvdBlue
        )
        if (ex.notes.isNotBlank()) {
            Text(
                "   ${ex.notes}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun MonthCard(monthNumber: Int, weeks: List<TrainingWeek>) {
    DecoratedCard(accentColor = MvdRed) {
        Text(
            "Месяц $monthNumber",
            style = MaterialTheme.typography.titleLarge,
            color = MvdBlue
        )
        Spacer(Modifier.height(6.dp))
        Text(
            "Недели: ${weeks.first().weekNumber}–${weeks.last().weekNumber}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        weeks.forEach { week ->
            Text(
                "• Неделя ${week.weekNumber} (${week.phase}) — 3 тренировки",
                style = MaterialTheme.typography.bodyMedium,
                color = MvdBlue
            )
        }
    }
}
