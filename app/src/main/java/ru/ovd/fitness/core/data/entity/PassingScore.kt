package ru.ovd.fitness.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Минимальное количество баллов для сдачи итоговых занятий.
 *
 * Источник: приказ МВД № 44, пункт 340.1.
 *
 * Для каждой комбинации (возрастная группа + пол + уровень подготовки)
 * указан минимум баллов, который сотрудник должен набрать.
 */
@Entity(tableName = "passing_scores")
data class PassingScore(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** Возрастная группа: 1..8 для мужчин, 1..7 для женщин */
    val groupNumber: Int,

    /** Пол: "male" / "female" */
    val gender: String,

    /** Уровень: "base" / "enhanced" / "special" */
    val level: String,

    /** Минимальный балл для сдачи */
    val minPoints: Int,

    /**
     * Рекомендуемый балл — минимум с запасом.
     * Пока запас +10% (округление вверх). Уточним позже.
     */
    val recommendedPoints: Int
)
