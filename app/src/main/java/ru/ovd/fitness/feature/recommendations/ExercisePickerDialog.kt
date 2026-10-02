package ru.ovd.fitness.feature.recommendations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.window.Dialog
import ru.ovd.fitness.core.data.entity.Exercise
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextPrimary
import ru.ovd.fitness.core.ui.theme.TextSecondary

/**
 * Диалог выбора упражнения.
 *
 * Показывает список всех доступных упражнений для текущего пола.
 * Уже выбранные упражнения — не показываются (или помечаются).
 */
@Composable
fun ExercisePickerDialog(
    exercises: List<Exercise>,
    alreadySelected: Set<Int>,   // orderNumber уже выбранных
    onDismiss: () -> Unit,
    onSelected: (Exercise) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(SurfaceWhite)
                .padding(20.dp)
        ) {
            // ─── Заголовок ───
            Text(
                text = "Выбор упражнения",
                style = MaterialTheme.typography.titleLarge,
                color = OvdDarkBlue,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ─── Список упражнений ───
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                exercises.forEach { exercise ->
                    val isSelected = exercise.orderNumber in alreadySelected

                    ExerciseItem(
                        exercise = exercise,
                        isSelected = isSelected,
                        onClick = {
                            if (!isSelected) {
                                onSelected(exercise)
                                onDismiss()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ─── Кнопка «Закрыть» ───
            Text(
                text = "Закрыть",
                color = OvdDarkBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(onClick = onDismiss)
                    .padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
private fun ExerciseItem(
    exercise: Exercise,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) TextSecondary.copy(alpha = 0.1f) else OvdLightBlue

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .clickable(enabled = !isSelected, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = exercise.name,
                color = if (isSelected) TextSecondary else TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${exercise.category} · ${exercise.unit}",
                color = TextSecondary,
                fontSize = 12.sp
            )
        }

        if (isSelected) {
            Text(
                text = "✓",
                color = TextSecondary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                text = "→",
                color = OvdDarkBlue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
