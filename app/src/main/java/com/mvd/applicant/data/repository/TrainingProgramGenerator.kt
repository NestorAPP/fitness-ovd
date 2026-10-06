package com.mvd.applicant.data.repository

import com.mvd.applicant.data.model.*
import com.mvd.applicant.data.tables.ScoreTables
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Генератор персональной программы тренировок.
 *
 * Логика:
 *  1. Считаем текущие баллы за три норматива.
 *  2. Считаем разрывы до максимума (33 / 33 / 34).
 *  3. Веса нормативов — пропорционально разрывам.
 *  4. Определяем фазы в зависимости от количества недель до экзамена:
 *     - > 12 недель: Базовая (4) + Развивающая (4–6) + Пиковая (2–3)
 *     - 4–12 недель: Базовая (2–3) + Развивающая (2–4) + Пиковая (1–2)
 *     - < 4 недель: только интенсивный курс с предупреждением
 *  5. Составляем недели: 3 тренировки в неделю.
 *  6. Распределяем упражнения пропорционально весам нормативов.
 */
class TrainingProgramGenerator {

    companion object {
        const val TARGET_STRENGTH = 33
        const val TARGET_SPEED = 33
        const val TARGET_ENDURANCE = 34
        const val MIN_WEEKS_WARNING = 4
    }

    fun generate(input: TrainingInput): TrainingProgram {
        val priority = calculatePriority(input)
        val weeksUntilExam = ChronoUnit.WEEKS
            .between(LocalDate.now(), input.examDate)
            .toInt()

        val weeks = when {
            weeksUntilExam <= 0 -> emptyList()
            weeksUntilExam < MIN_WEEKS_WARNING -> generateIntensiveCourse(input, priority, weeksUntilExam)
            weeksUntilExam <= 12 -> generateShortProgram(input, priority, weeksUntilExam)
            else -> generateFullProgram(input, priority, weeksUntilExam)
        }

        return TrainingProgram(
            input = input,
            priority = priority,
            weeks = weeks,
            generatedAt = LocalDate.now(),
            lastUpdatedAt = LocalDate.now()
        )
    }

    // ============================================================
    // РАСЧЁТ ПРИОРИТЕТОВ
    // ============================================================

    private fun calculatePriority(input: TrainingInput): TrainingPriority {
        val strengthPoints = when (input.gender) {
            Gender.MALE -> ScoreTables.scorePullups(input.strengthValue, input.group)
            Gender.FEMALE -> ScoreTables.scoreSku(input.strengthValue, input.group)
        }
        val speedPoints = ScoreTables.scoreRun100(input.run100Seconds, input.gender, input.group)
        val endurancePoints = ScoreTables.scoreRun1000(input.run1000Seconds, input.gender, input.group)

        val strengthGap = (TARGET_STRENGTH - strengthPoints).coerceAtLeast(0)
        val speedGap = (TARGET_SPEED - speedPoints).coerceAtLeast(0)
        val enduranceGap = (TARGET_ENDURANCE - endurancePoints).coerceAtLeast(0)

        val totalGap = (strengthGap + speedGap + enduranceGap).coerceAtLeast(1)

        val strengthWeight = strengthGap.toFloat() / totalGap
        val speedWeight = speedGap.toFloat() / totalGap
        val enduranceWeight = enduranceGap.toFloat() / totalGap

        return TrainingPriority(
            strengthPoints = strengthPoints,
            speedPoints = speedPoints,
            endurancePoints = endurancePoints,
            strengthGap = strengthGap,
            speedGap = speedGap,
            enduranceGap = enduranceGap,
            strengthWeight = strengthWeight,
            speedWeight = speedWeight,
            enduranceWeight = enduranceWeight
        )
    }

    // ============================================================
    // ПОЛНАЯ ПРОГРАММА (> 12 недель)
    // ============================================================

    private fun generateFullProgram(
        input: TrainingInput,
        priority: TrainingPriority,
        weeksUntilExam: Int
    ): List<TrainingWeek> {
        val weeks = mutableListOf<TrainingWeek>()

        val baseWeeks = 4
        val peakWeeks = 3
        val developWeeks = (weeksUntilExam - baseWeeks - peakWeeks).coerceAtLeast(4)

        var weekNum = 1

        // Базовая фаза
        repeat(baseWeeks) {
            weeks.add(buildWeek(weekNum++, "Базовая", input, priority, baseIntensity = true))
        }
        // Развивающая фаза
        repeat(developWeeks) {
            weeks.add(buildWeek(weekNum++, "Развивающая", input, priority, baseIntensity = false))
        }
        // Пиковая фаза
        repeat(peakWeeks) {
            weeks.add(buildWeek(weekNum++, "Пиковая", input, priority, baseIntensity = false))
        }

        return weeks
    }

    // ============================================================
    // КОРОТКАЯ ПРОГРАММА (4–12 недель)
    // ============================================================

    private fun generateShortProgram(
        input: TrainingInput,
        priority: TrainingPriority,
        weeksUntilExam: Int
    ): List<TrainingWeek> {
        val weeks = mutableListOf<TrainingWeek>()

        val baseWeeks = 2
        val peakWeeks = 1
        val developWeeks = (weeksUntilExam - baseWeeks - peakWeeks).coerceAtLeast(1)

        var weekNum = 1

        repeat(baseWeeks) {
            weeks.add(buildWeek(weekNum++, "Базовая", input, priority, baseIntensity = true))
        }
        repeat(developWeeks) {
            weeks.add(buildWeek(weekNum++, "Развивающая", input, priority, baseIntensity = false))
        }
        repeat(peakWeeks) {
            weeks.add(buildWeek(weekNum++, "Пиковая", input, priority, baseIntensity = false))
        }

        return weeks
    }

    // ============================================================
    // ИНТЕНСИВНЫЙ КУРС (< 4 недель)
    // ============================================================

    private fun generateIntensiveCourse(
        input: TrainingInput,
        priority: TrainingPriority,
        weeksUntilExam: Int
    ): List<TrainingWeek> {
        return (1..weeksUntilExam).map { weekNum ->
            buildWeek(weekNum, "Интенсивная", input, priority, baseIntensity = false)
        }
    }

    // ============================================================
    // СБОРКА ОДНОЙ НЕДЕЛИ
    // ============================================================

    private fun buildWeek(
        weekNum: Int,
        phase: String,
        input: TrainingInput,
        priority: TrainingPriority,
        baseIntensity: Boolean
    ): TrainingWeek {
        val note = when {
            weekNum == 1 -> "Старт программы. Нагрузка умеренная, привыкаем к режиму."
            phase == "Пиковая" -> "Подводим форму к экзамену. Снижаем объём, следим за восстановлением."
            phase == "Интенсивная" -> "Сжатые сроки. Акцент на технику и восстановление."
            else -> "Плановая тренировочная неделя."
        }

        return TrainingWeek(
            weekNumber = weekNum,
            phase = phase,
            days = listOf(
                buildDayStrengthAndSpeed(input, priority, baseIntensity),
                buildDayEnduranceAndSpeed(input, priority, baseIntensity),
                buildDayMixed(input, priority, baseIntensity)
            ),
            notes = note
        )
    }

    // ============================================================
    // ДЕНЬ 1 — СИЛА + СКОРОСТЬ
    // ============================================================

    private fun buildDayStrengthAndSpeed(
        input: TrainingInput,
        priority: TrainingPriority,
        baseIntensity: Boolean
    ): TrainingDay {
        val strengthVolume = when {
            priority.strengthWeight > 0.5f -> if (baseIntensity) 3 else 5
            priority.strengthWeight > 0.25f -> if (baseIntensity) 3 else 4
            else -> if (baseIntensity) 2 else 3
        }
        val reps = when {
            baseIntensity -> "6–8"
            else -> "5–6"
        }

        val isMale = input.gender == Gender.MALE
        val strengthExercise = if (isMale) {
            Exercise("Подтягивания на перекладине", strengthVolume, reps, 90,
                "Работай по полной амплитуде: подбородок выше грифа.")
        } else {
            Exercise("Сгибание-разгибание рук в упоре лёжа", strengthVolume, reps, 60,
                "Туловище прямое, локти не разводить более 45°.")
        }

        val additionalStrength = if (isMale) {
            Exercise("Отжимания от пола", 3, reps, 60, "Дополнительная нагрузка на руки.")
        } else {
            Exercise("Наклоны вперёд лёжа на спине", 3, "12–15", 45,
                "Касание пальцев ног, лопатки на полу.")
        }

        val speedVolume = when {
            priority.speedWeight > 0.5f -> if (baseIntensity) 4 else 6
            priority.speedWeight > 0.25f -> if (baseIntensity) 3 else 5
            else -> if (baseIntensity) 3 else 4
        }
        val speedDist = if (baseIntensity) "30 м" else "60 м"

        return TrainingDay(
            dayName = "День 1",
            focus = "Сила + Скорость",
            warmup = "Разминка 10 мин: лёгкий бег, суставная гимнастика, 4×20 м ускорения.",
            exercises = listOf(
                strengthExercise,
                additionalStrength,
                Exercise("Ускорения $speedDist", speedVolume, "$speedVolume×$speedDist", 120,
                    "Максимальная скорость, полный отдых между отрезками.")
            ),
            cooldown = "Заминка 8–10 мин: спокойный бег, растяжка."
        )
    }

    // ============================================================
    // ДЕНЬ 2 — ВЫНОСЛИВОСТЬ + СКОРОСТЬ
    // ============================================================

    private fun buildDayEnduranceAndSpeed(
        input: TrainingInput,
        priority: TrainingPriority,
        baseIntensity: Boolean
    ): TrainingDay {
        val enduranceVolume = when {
            priority.enduranceWeight > 0.5f -> if (baseIntensity) "3 км" else "5 км"
            priority.enduranceWeight > 0.25f -> if (baseIntensity) "3 км" else "4 км"
            else -> if (baseIntensity) "2 км" else "3 км"
        }

        val speedVolume = when {
            priority.speedWeight > 0.5f -> if (baseIntensity) 5 else 7
            priority.speedWeight > 0.25f -> if (baseIntensity) 4 else 6
            else -> if (baseIntensity) 3 else 5
        }

        return TrainingDay(
            dayName = "День 2",
            focus = "Выносливость + Скорость",
            warmup = "Разминка 10 мин: лёгкий бег 1 км, суставная гимнастика.",
            exercises = listOf(
                Exercise("Кросс $enduranceVolume", 1, enduranceVolume, 0,
                    "Ровный темп, дыхание через нос. Если тяжело — переходи на шаг."),
                Exercise("Интервальный бег 200 м", speedVolume, "$speedVolume×200 м", 90,
                    "Работа на скорости близкой к максимальной, отдых — ходьба."),
                Exercise("Ускорения 100 м", 3, "3×100 м", 120,
                    "Финальные 20 метров — на максимальной скорости.")
            ),
            cooldown = "Заминка 10 мин: медленный бег, растяжка ног."
        )
    }

    // ============================================================
    // ДЕНЬ 3 — СМЕШАННЫЙ
    // ============================================================

    private fun buildDayMixed(
        input: TrainingInput,
        priority: TrainingPriority,
        baseIntensity: Boolean
    ): TrainingDay {
        val isMale = input.gender == Gender.MALE

        val strengthExercise = if (isMale) {
            Exercise("Подтягивания с паузой", 3, "4–6", 90,
                "Держим положение 1 сек в верхней точке.")
        } else {
            Exercise("СКУ (тренировочный вариант)", 3, "на максимум", 120,
                "1 минута: 30 сек наклоны + 30 сек отжимания.")
        }

        val baseEndurance = if (baseIntensity) "2 км" else "3 км"

        return TrainingDay(
            dayName = "День 3",
            focus = "Смешанная тренировка",
            warmup = "Разминка 10 мин: лёгкий бег, суставная гимнастика, динамическая растяжка.",
            exercises = listOf(
                strengthExercise,
                Exercise("Челночный бег 10×10 м", 3, "3×10×10 м", 90,
                    "Работа на резкость, разворот — низкий."),
                Exercise("Кросс $baseEndurance", 1, baseEndurance, 0,
                    "Восстановительный темп. Главная цель — не устать."),
                Exercise("Планка", 3, "60 сек", 45,
                    "Пресс, спина, ягодицы в тонусе.")
            ),
            cooldown = "Заминка 8 мин: растяжка всего тела, дыхательные упражнения."
        )
    }
}
