package ru.ovd.fitness.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Уровень физической подготовки сотрудника.
 *
 * Из приказа МВД № 44:
 *   1. Базовый       — для сотрудников, не являющихся сотрудниками полиции,
 *                      и 4 группы предназначения.
 *   2. Усиленный     — для сотрудников полиции (1–3 группы предназначения).
 *   3. Специальный   — для спецподразделений (СОБР, ОМОН и т.д.).
 */
@Entity(tableName = "fitness_levels")
data class FitnessLevel(
    @PrimaryKey
    val id: Int,              // 1, 2, 3

    /** Короткое название: "Базовый", "Усиленный", "Специальный" */
    val name: String,

    /** Пояснение — что это за уровень и для кого */
    val description: String
)
