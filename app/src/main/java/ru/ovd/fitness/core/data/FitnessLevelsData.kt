package ru.ovd.fitness.core.data

import ru.ovd.fitness.core.data.entity.FitnessLevel

/**
 * Уровни физической подготовки (приказ МВД № 44).
 */
val fitnessLevelsData: List<FitnessLevel> = listOf(
    FitnessLevel(
        id = 1,
        name = "Базовый",
        description = "Для сотрудников, не являющихся сотрудниками полиции, и 4 группы предназначения"
    ),
    FitnessLevel(
        id = 2,
        name = "Усиленный",
        description = "Для сотрудников полиции (1–3 группы предназначения)"
    ),
    FitnessLevel(
        id = 3,
        name = "Специальный",
        description = "Для спецподразделений (СОБР, ОМОН и т.д.)"
    )
)
