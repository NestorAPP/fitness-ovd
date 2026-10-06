package com.mvd.applicant.ui.training

import androidx.lifecycle.ViewModel
import com.mvd.applicant.data.model.Gender
import com.mvd.applicant.data.model.PurposeGroup
import com.mvd.applicant.data.model.TrainingInput
import com.mvd.applicant.data.model.TrainingProgram
import com.mvd.applicant.data.repository.TrainingProgramGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class ProgramViewMode { WEEKLY, MONTHLY }

data class TrainingUiState(
    val gender: Gender = Gender.MALE,
    val group: PurposeGroup = PurposeGroup.GROUP_1_2,
    val strengthValue: Int = 10,
    val run100Seconds: Double = 15.0,
    val run1000Minutes: Int = 4,
    val run1000Seconds: Int = 30,
    val examDate: LocalDate? = null,
    val program: TrainingProgram? = null,
    val viewMode: ProgramViewMode = ProgramViewMode.WEEKLY,
    val warning: String? = null,
    val error: String? = null
)

class TrainingViewModel(
    private val generator: TrainingProgramGenerator = TrainingProgramGenerator()
) : ViewModel() {

    private val _state = MutableStateFlow(TrainingUiState())
    val state: StateFlow<TrainingUiState> = _state.asStateFlow()

    fun setGender(g: Gender) {
        val defaultStrength = if (g == Gender.MALE) 10 else 30
        _state.value = _state.value.copy(
            gender = g,
            strengthValue = defaultStrength,
            program = null,
            warning = null
        )
    }

    fun setGroup(g: PurposeGroup) {
        _state.value = _state.value.copy(group = g, program = null, warning = null)
    }

    fun setStrength(v: Int) {
        _state.value = _state.value.copy(strengthValue = v, program = null, warning = null)
    }

    fun setRun100(v: Double) {
        _state.value = _state.value.copy(run100Seconds = v, program = null, warning = null)
    }

    fun setRun1000Minutes(v: Int) {
        _state.value = _state.value.copy(run1000Minutes = v, program = null, warning = null)
    }

    fun setRun1000Seconds(v: Int) {
        _state.value = _state.value.copy(run1000Seconds = v, program = null, warning = null)
    }

    fun setExamDate(date: LocalDate) {
        _state.value = _state.value.copy(examDate = date, program = null, warning = null)
    }

    fun setViewMode(mode: ProgramViewMode) {
        _state.value = _state.value.copy(viewMode = mode)
    }

    fun resetProgram() {
        _state.value = _state.value.copy(program = null, warning = null, error = null)
    }

    fun calculateProgram() {
        val s = _state.value
        val examDate = s.examDate

        if (examDate == null) {
            _state.value = s.copy(error = "Выберите дату вступительного испытания")
            return
        }

        if (examDate.isBefore(LocalDate.now())) {
            _state.value = s.copy(error = "Дата экзамена уже прошла")
            return
        }

        val input = TrainingInput(
            gender = s.gender,
            group = s.group,
            strengthValue = s.strengthValue,
            run100Seconds = s.run100Seconds,
            run1000Seconds = s.run1000Minutes * 60 + s.run1000Seconds,
            examDate = examDate
        )

        val program = generator.generate(input)
        val weeksUntilExam = program.totalWeeks

        val warning = if (weeksUntilExam < TrainingProgramGenerator.MIN_WEEKS_WARNING) {
            "Слишком мало времени, рекомендуется продлить подготовку."
        } else null

        _state.value = s.copy(
            program = program,
            warning = warning,
            error = null
        )
    }

    fun formatExamDate(): String {
        val date = _state.value.examDate ?: return "не выбрана"
        return date.format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru")))
    }

    fun localDateToMillis(date: LocalDate): Long {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    fun millisToLocalDate(millis: Long): LocalDate {
        return java.time.Instant.ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
}
