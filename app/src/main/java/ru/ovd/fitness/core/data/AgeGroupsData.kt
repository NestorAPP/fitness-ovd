package ru.ovd.fitness.core.data

import ru.ovd.fitness.core.data.entity.AgeGroup

/**
 * Возрастные группы сотрудников (приказ МВД № 44).
 *
 * Мужчины — 8 групп, женщины — 7 групп.
 * Возраст: нижняя граница включительно, верхняя — включительно.
 * Для последней группы maxAge = 999 (означает «и старше»).
 */
val ageGroupsData: List<AgeGroup> = listOf(

    // ─────────── МУЖЧИНЫ ───────────
    AgeGroup(groupNumber = 1, gender = "male", minAge = 18, maxAge = 24, label = "до 25 лет"),
    AgeGroup(groupNumber = 2, gender = "male", minAge = 25, maxAge = 29, label = "25–30 лет"),
    AgeGroup(groupNumber = 3, gender = "male", minAge = 30, maxAge = 34, label = "30–35 лет"),
    AgeGroup(groupNumber = 4, gender = "male", minAge = 35, maxAge = 39, label = "35–40 лет"),
    AgeGroup(groupNumber = 5, gender = "male", minAge = 40, maxAge = 44, label = "40–45 лет"),
    AgeGroup(groupNumber = 6, gender = "male", minAge = 45, maxAge = 49, label = "45–50 лет"),
    AgeGroup(groupNumber = 7, gender = "male", minAge = 50, maxAge = 54, label = "50–55 лет"),
    AgeGroup(groupNumber = 8, gender = "male", minAge = 55, maxAge = 999, label = "55 лет и старше"),

    // ─────────── ЖЕНЩИНЫ ───────────
    AgeGroup(groupNumber = 1, gender = "female", minAge = 18, maxAge = 24, label = "до 25 лет"),
    AgeGroup(groupNumber = 2, gender = "female", minAge = 25, maxAge = 29, label = "25–30 лет"),
    AgeGroup(groupNumber = 3, gender = "female", minAge = 30, maxAge = 34, label = "30–35 лет"),
    AgeGroup(groupNumber = 4, gender = "female", minAge = 35, maxAge = 39, label = "35–40 лет"),
    AgeGroup(groupNumber = 5, gender = "female", minAge = 40, maxAge = 44, label = "40–45 лет"),
    AgeGroup(groupNumber = 6, gender = "female", minAge = 45, maxAge = 49, label = "45–50 лет"),
    AgeGroup(groupNumber = 7, gender = "female", minAge = 50, maxAge = 999, label = "50 лет и старше")
)
