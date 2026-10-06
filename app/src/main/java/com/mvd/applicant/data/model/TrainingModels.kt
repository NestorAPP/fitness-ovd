package com.mvd.applicant.data.model

import java.time.LocalDate

/**
 * Входные данные для генератора программы тренировок.
 */
data class TrainingInput(
    val gender: Gender,
    val group: PurposeGroup,
    val strengthValue: Int,        // подтягивания / СКУ
    val run100Seconds: Double,
    val run1000Seconds: Int,       // всего секунд
    val examDate: LocalDate
)

/**
 * Результат расчёта приоритетов и разрывов.
 *
 * Целевые баллы: сила = 33, скорость = 33, выносливость = 34.
 */
data class TrainingPriority(
    val strengthPoints: Int,
    val speedPoints: Int,
    val endurancePoints: Int,
    val strengthGap: Int,          // 33 - strengthPoints
    val speedGap: Int,             // 33 - speedPoints
    val enduranceGap: Int,         // 34 - endurancePoints
    val strengthWeight: Float,     // вес норматива (0..1)
    val speedWeight: Float,
    val enduranceWeight: Float
) {
    /** Слабые нормативы — те, что требуют больше внимания. */
    val focusAreas: List<String>
        get() = buildList {
            val maxWeight = maxOf(strengthWeight, speedWeight, enduranceWeight)
            if (strengthWeight == maxWeight) add("СИЛА")
            if (speedWeight == maxWeight) add("СКОРОСТЬ")
            if (enduranceWeight == maxWeight) add("ВЫНОСЛИВОСТЬ")
        }
}

/**
 * Одно упражнение в тренировке.
 */
data class Exercise(
    val name: String,
    val sets: Int,
    val reps: String,               // "5", "6×30 м", "3 км"
    val restSeconds: Int,
    val notes: String = ""
)

/**
 * Одна тренировка (один день).
 */
data class TrainingDay(
    val dayName: String,            // "Понедельник"
    val focus: String,              // "Сила + Скорость"
    val warmup: String,
    val exercises: List<Exercise>,
    val cooldown: String
)

/**
 * Одна неделя программы.
 */
data class TrainingWeek(
    val weekNumber: Int,
    val phase: String,              // "Базовая", "Развивающая", "Пиковая"
    val days: List<TrainingDay>,
    val notes: String = ""
)

/**
 * Вся программа тренировок.
 */
data class TrainingProgram(
    val input: TrainingInput,
    val priority: TrainingPriority,
    val weeks: List<TrainingWeek>,
    val generatedAt: LocalDate,
    val lastUpdatedAt: LocalDate
) {
    val totalWeeks: Int get() = weeks.size
}
