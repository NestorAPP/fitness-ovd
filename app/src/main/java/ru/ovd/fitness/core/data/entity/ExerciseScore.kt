package ru.ovd.fitness.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Балл за результат выполнения контрольного упражнения.
 *
 * Источник: приказ МВД № 44, пункт 343.
 *
 * Одна строка = один балл одного упражнения для одного пола.
 *
 * Связь с упражнением — через пару (gender, orderNumber):
 *   - gender = "male" / "female"
 *   - orderNumber = 1..9 (для мужчин), 1..7 (для женщин)
 *
 * Пример (мужчины, подтягивание, orderNumber = 1):
 *   points = 100, resultDisplay = "более 22"
 *   points = 99,  resultDisplay = "22"
 *   points = 98,  resultDisplay = "21"
 *   points = 97,  resultDisplay = "—" (прочерк)
 *
 * Для числовых сравнений (в калькуляторе) храним значения в формате
 * приказа как Double:
 *   "10.31" (10 мин 31 сек) → resultMinSec = 10.31, resultMaxSec = null
 *   "10.31 - 10.34"          → resultMinSec = 10.31, resultMaxSec = 10.34
 *   "22"  (22 раза)          → resultMinSec = 22.0,  resultMaxSec = 22.0
 *   "—"   (прочерк)          → resultMinSec = null,  resultMaxSec = null
 */
@Entity(tableName = "exercise_scores")
data class ExerciseScore(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** Пол: "male" / "female" */
    val gender: String,

    /** Порядковый номер упражнения: 1..9 (муж), 1..7 (жен) */
    val orderNumber: Int,

    /** Балл: 0..100 */
    val points: Int,

    /** Текст для отображения: "более 22", "10.31 - 10.34", "—" */
    val resultDisplay: String,

    /** Минимальное значение (null — нет значения) */
    val resultMinSec: Double?,

    /** Максимальное значение (null — одиночное значение или нет) */
    val resultMaxSec: Double?,

    /** Единица измерения: "раз", "секунд", "минут, секунд" */
    val resultUnit: String
)
