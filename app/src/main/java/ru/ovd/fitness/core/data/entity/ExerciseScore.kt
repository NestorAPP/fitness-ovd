package ru.ovd.fitness.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Балл за результат выполнения контрольного упражнения.
 *
 * Источник: приказ МВД № 44, пункт 343.
 *
 * Одна строка = один балл одного упражнения для одной возрастной группы.
 *
 * Пример (мужчины, подтягивание):
 *   points = 100, resultDisplay = "более 22"
 *   points = 99,  resultDisplay = "22"
 *   points = 98,  resultDisplay = "21"
 *   points = 97,  resultDisplay = "—" (прочерк)
 *
 * Для числовых сравнений (в калькуляторе) храним секунды:
 *   "10.31" (10 мин 31 сек) → resultMinSec = 631
 *   "10.31 — 10.34" → resultMinSec = 631, resultMaxSec = 634
 *   "22" → resultMinSec = 22, resultMaxSec = 22
 *
 * Если балл не даётся за результат (прочерк) — resultMinSec = null.
 */
@Entity(tableName = "exercise_scores")
data class ExerciseScore(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** Ссылка на упражнение (Exercise.id) */
    val exerciseId: Int,

    /** Пол: "male" / "female" */
    val gender: String,

    /** Балл: 0..100 */
    val points: Int,

    /** Текст для отображения: "более 22", "10.31 — 10.34", "—" */
    val resultDisplay: String,

    /** Минимальное значение в секундах (null — нет значения) */
    val resultMinSec: Int?,

    /** Максимальное значение в секундах (null — одиночное значение или нет) */
    val resultMaxSec: Int?,

    /** Единица измерения: "раз", "секунд", "минут, секунд" */
    val resultUnit: String
)
