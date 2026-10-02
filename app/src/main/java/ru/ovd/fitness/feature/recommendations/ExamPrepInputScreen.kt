package ru.ovd.fitness.feature.recommendations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.data.FitnessRepository
import ru.ovd.fitness.core.data.UserPreferences
import ru.ovd.fitness.core.data.entity.Exercise
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextPrimary
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriWhite
import ru.ovd.fitness.feature.fitness.HeaderBlock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ExamPrepInputScreen(
    navController: NavHostController,
    viewModel: ExamPrepInputViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val prefs = UserPreferences(context)
    val repository = remember { FitnessRepository(context) }

    var allExercises by remember { mutableStateOf<List<Exercise>>(emptyList()) }
    var pickerOpen by remember { mutableStateOf(false) }
    var datePickerOpen by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            allExercises = repository.getExercises(prefs.getGender())
        } catch (_: Exception) {
            allExercises = emptyList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .verticalScroll(rememberScrollState())
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderBlock(title = "Подготовка к итоговым")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 24.dp)
        ) {
            SectionTitle("Тип испытания")
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceWhite),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TypeToggleSide("Итоговые занятия", state.examType == ExamType.FINAL, Modifier.weight(1f)) {
                    viewModel.setExamType(ExamType.FINAL)
                }
                TypeToggleSide("На звание", state.examType == ExamType.QUALIFICATION, Modifier.weight(1f)) {
                    viewModel.setExamType(ExamType.QUALIFICATION)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("Дата сдачи")
            Spacer(modifier = Modifier.height(10.dp))
            DateField(state.examDateMillis) { datePickerOpen = true }

            state.daysLeft?.let { days ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (days > 0) "Осталось дней: $days" else "Дата уже прошла",
                    color = if (days > 0) TextSecondary else MaterialTheme.colorScheme.error,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (state.examType == ExamType.QUALIFICATION) {
                SectionTitle("Целевое звание")
                Spacer(modifier = Modifier.height(10.dp))
                QualificationPicker(state.qualificationName) { viewModel.setQualificationName(it) }
                Spacer(modifier = Modifier.height(24.dp))
            }

            SectionTitle("Твои упражнения (максимум ${state.maxExercises})")
            Spacer(modifier = Modifier.height(10.dp))

            state.selectedExercises.forEach { ex ->
                SelectedExerciseCard(
                    exercise = ex,
                    onRemove = { viewModel.removeExercise(ex.orderNumber) },
                    onResultChange = { viewModel.updateResult(ex.orderNumber, it) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            if (state.selectedExercises.size < state.maxExercises) {
                AddExerciseButton { pickerOpen = true }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // ─── СОХРАНЯЕМ ДАННЫЕ ───
                    prefs.setExamType(state.examType.name)
                    state.examDateMillis?.let { prefs.setExamDate(it) }
                    prefs.setQualificationName(state.qualificationName)

                    val serialized = state.selectedExercises.joinToString("||") {
                        "${it.orderNumber}|${it.name}|${it.category}|${it.unit}|${it.currentResult}"
                    }
                    prefs.setSelectedExercises(serialized)

                    navController.navigate("exam_prep_result")
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OvdDarkBlue,
                    contentColor = TriWhite
                ),
                enabled = state.isValid
            ) {
                Text(
                    text = "Показать программу",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    if (pickerOpen) {
        ExercisePickerDialog(
            exercises = allExercises,
            alreadySelected = state.selectedExercises.map { it.orderNumber }.toSet(),
            onDismiss = { pickerOpen = false },
            onSelected = { ex ->
                viewModel.addExercise(
                    SelectedExercise(
                        orderNumber = ex.orderNumber,
                        name = ex.name,
                        category = ex.category,
                        unit = ex.unit
                    )
                )
            }
        )
    }

    if (datePickerOpen) {
        DatePickerDialogWrapper(
            initialMillis = state.examDateMillis,
            onDismiss = { datePickerOpen = false },
            onConfirm = { millis ->
                viewModel.setExamDate(millis)
                datePickerOpen = false
            }
        )
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = OvdDarkBlue,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun TypeToggleSide(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bg = if (selected) OvdDarkBlue else Color.Transparent
    val fg = if (selected) TriWhite else TextSecondary
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = fg, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal, fontSize = 14.sp)
    }
}

@Composable
private fun DateField(millis: Long?, onClick: () -> Unit) {
    val dateText = if (millis != null) {
        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(millis))
    } else "Выбрать дату"

    Row(
        modifier = Modifier
            .fillMaxWidth().height(56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceWhite)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dateText,
            color = if (millis != null) TextPrimary else TextSecondary,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Text(text = "📅", fontSize = 20.sp)
    }
}

@Composable
private fun QualificationPicker(selected: String?, onSelect: (String) -> Unit) {
    val qualifications = listOf(
        "Специалист третьего класса",
        "Специалист второго класса",
        "Специалист первого класса",
        "Мастер"
    )
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        qualifications.forEach { q ->
            val isSelected = selected == q
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) OvdLightBlue else SurfaceWhite)
                    .clickable { onSelect(q) }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp).clip(CircleShape)
                        .background(if (isSelected) OvdDarkBlue else TextSecondary.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSelected) Box(Modifier.size(8.dp).clip(CircleShape).background(TriWhite))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = q, color = OvdDarkBlue, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal, fontSize = 15.sp)
            }
        }
    }
}

@Composable
private fun SelectedExerciseCard(
    exercise: SelectedExercise,
    onRemove: () -> Unit,
    onResultChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(OvdLightBlue)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = exercise.name, color = OvdDarkBlue, fontWeight = FontWeight.Bold, fontSize = 15.sp, lineHeight = 20.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "${exercise.category} · ${exercise.unit}", color = TextSecondary, fontSize = 12.sp)
            }
            Text(
                text = "✕", color = TextSecondary, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.clip(CircleShape).clickable(onClick = onRemove).padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = exercise.currentResult,
            onValueChange = onResultChange,
            label = { Text("Текущий результат") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun AddExerciseButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth().height(56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceWhite)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "＋  Добавить упражнение", color = OvdDarkBlue, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}
