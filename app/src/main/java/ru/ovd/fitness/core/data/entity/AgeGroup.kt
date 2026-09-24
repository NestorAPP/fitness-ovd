package ru.ovd.fitness.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Возрастная группа сотрудника.
 *
 * Мужчины: 8 групп.
 * Женщины: 7 групп.
 *
 * Диапазоны из приказа МВД № 44:
 * Мужчины:
 *   1: 18–24,  2: 25–29,  3: 30–34,  4: 35–39,
 *   5: 40–44,  6: 45–49,  7: 50–54,  8: 55+
 * Женщины:
 *   1: 18–24,  2: 25–29,  3: 30–34,  4: 35–39,
 *   5: 40–44,  6: 45–49,  7: 50+
 */
@Entity(tableName = "age_groups")
data class AgeGroup(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** Номер группы: 1..8 для мужчин, 1..7 для женщин */
    val groupNumber: Int,

    /** Пол: "male" / "female" */
    val gender: String,

    /** Минимальный возраст (включительно) */
    val minAge: Int,

    /** Максимальный возраст (включительно). 999 = «и старше» */
    val maxAge: Int,

    /** Текст для отображения: "до 25 лет", "25–30 лет" и т.д. */
    val label: String
)
