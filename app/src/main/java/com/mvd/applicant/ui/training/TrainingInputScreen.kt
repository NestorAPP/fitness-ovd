package com.mvd.applicant.ui.training

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mvd.applicant.data.model.Gender
import com.mvd.applicant.data.model.PurposeGroup
import com.mvd.applicant.ui.theme.MvdBlue
import com.mvd.applicant.ui.theme.MvdRed
import com.mvd.applicant.ui.widgets.DecoratedCard
import com.mvd.applicant.ui.widgets.SectionTitle
import com.mvd.applicant.ui.widgets.SubsectionTitle
import com.mvd.applicant.ui.widgets.WheelPicker
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingInputScreen(
    vm: TrainingViewModel = viewModel(),
    onProgramReady: () -> Unit = {}
) {
    val state by vm.state.collectAsState()

    val strengthValues = remember(state.gender) {
        if (state.gender == Gender.MALE) (0..50).toList() else (0..60).toList()
    }
    val strengthLabels = remember(strengthValues) { strengthValues.map { it.toString() } }
    val strengthIndex = strengthValues.indexOf(state.strengthValue).coerceAtLeast(0)

    val run100Values = remember { (100..250).map { it / 10.0 } }
    val run100Labels = remember { run100Values.map { "%.1f".format(it) } }
    val run100Index = run100Values
        .indexOfFirst { abs(it - state.run100Seconds) < 0.001 }
        .coerceAtLeast(0)

    val run1000MinValues = remember { (2..8).toList() }
    val run1000MinLabels = remember { run1000MinValues.map { it.toString() } }
    val run1000MinIndex = run1000MinValues.indexOf(state.run1000Minutes).coerceAtLeast(0)

    val run1000SecValues = remember { (0..59).toList() }
    val run1000SecLabels = remember { run1000SecValues.map { "%02d".format(it) } }
    val run1000SecIndex = run1000SecValues.indexOf(state.run1000Seconds).coerceAtLeast(0)

    var showDatePicker by remember { androidx.compose.runtime.mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionTitle("Программа подготовки")

        Text(
            "Заполните данные — приложение построит персональный план тренировок до даты экзамена.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Пол
        SubsectionTitle("Пол")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = state.gender == Gender.MALE,
                onClick = { vm.setGender(Gender.MALE) },
                label = { Text("Юноша") }
            )
            FilterChip(
                selected = state.gender == Gender.FEMALE,
                onClick = { vm.setGender(Gender.FEMALE) },
                label = { Text("Девушка") }
            )
        }

        // Группа
        SubsectionTitle("Группа предназначения")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = state.group == PurposeGroup.GROUP_1_2,
                onClick = { vm.setGroup(PurposeGroup.GROUP_1_2) },
                label = { Text("1-2 группа") }
            )
            FilterChip(
                selected = state.group == PurposeGroup.GROUP_3_4,
                onClick = { vm.setGroup(PurposeGroup.GROUP_3_4) },
                label = { Text("3-4 группа") }
            )
        }

        // Текущие результаты
        DecoratedCard {
            val title = if (state.gender == Gender.MALE)
                "Текущий результат: Подтягивание (раз)"
            else "Текущий результат: СКУ (раз)"
            SubsectionTitle(title)
            Spacer(Modifier.height(8.dp))
            WheelPicker(
                items = strengthLabels,
                selectedIndex = strengthIndex,
                onSelectedIndexChange = { vm.setStrength(strengthValues[it]) }
            )
        }

        DecoratedCard(accentColor = MvdRed) {
            SubsectionTitle("Текущий результат: Бег 100 м (сек)")
            Spacer(Modifier.height(8.dp))
            WheelPicker(
                items = run100Labels,
                selectedIndex = run100Index,
                onSelectedIndexChange = { vm.setRun100(run100Values[it]) }
            )
        }

        DecoratedCard {
            SubsectionTitle("Текущий результат: Бег 1000 м")
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                WheelPicker(
                    items = run1000MinLabels,
                    selectedIndex = run1000MinIndex,
                    onSelectedIndexChange = { vm.setRun1000Minutes(run1000MinValues[it]) },
                    modifier = Modifier.weight(1f)
                )
                Text(":", style = MaterialTheme.typography.headlineMedium, color = MvdBlue)
                WheelPicker(
                    items = run1000SecLabels,
                    selectedIndex = run1000SecIndex,
                    onSelectedIndexChange = { vm.setRun1000Seconds(run1000SecValues[it]) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Дата экзамена
        SubsectionTitle("Дата вступительного испытания")
        OutlinedButton(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Выбрать дату: ${vm.formatExamDate()}")
        }

        if (showDatePicker) {
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = state.examDate?.let { vm.localDateToMillis(it) }
            )
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let {
                            vm.setExamDate(vm.millisToLocalDate(it))
                        }
                        showDatePicker = false
                    }) {
                        Text("ОК")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Отмена")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        // Ошибка
        state.error?.let {
            Card(
                colors = CardDefaults.cardColors(containerColor = MvdRed.copy(alpha = 0.1f))
            ) {
                Text(it, color = MvdRed, modifier = Modifier.padding(12.dp))
            }
        }

        // Кнопка
        Button(
            onClick = {
                vm.calculateProgram()
                if (vm.state.value.program != null) onProgramReady()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MvdBlue)
        ) {
            Text("Рассчитать программу подготовки", color = Color.White)
        }

        Spacer(Modifier.height(24.dp))
    }
}
