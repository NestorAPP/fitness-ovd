package ru.ovd.fitness.core.data

import ru.ovd.fitness.core.data.entity.PassingScore

/**
 * Минимальные баллы для сдачи итоговых занятий.
 *
 * Источник: приказ МВД № 44, пункт 340.1.
 *
 * Формат: (группа, пол, уровень) -> минимальный балл.
 * recommendedPoints = minPoints + 10% (округление вверх) — рекомендация запаса.
 */
val passingScoresData: List<PassingScore> = listOf(

    // ═══════════════ МУЖЧИНЫ ═══════════════
    // 1 группа (до 25)
    PassingScore(groupNumber = 1, gender = "male", level = "base",     minPoints = 55,  recommendedPoints = 61),
    PassingScore(groupNumber = 1, gender = "male", level = "enhanced", minPoints = 115, recommendedPoints = 127),
    PassingScore(groupNumber = 1, gender = "male", level = "special",  minPoints = 180, recommendedPoints = 198),
    // 2 группа (25–30)
    PassingScore(groupNumber = 2, gender = "male", level = "base",     minPoints = 50,  recommendedPoints = 55),
    PassingScore(groupNumber = 2, gender = "male", level = "enhanced", minPoints = 105, recommendedPoints = 116),
    PassingScore(groupNumber = 2, gender = "male", level = "special",  minPoints = 165, recommendedPoints = 182),
    // 3 группа (30–35)
    PassingScore(groupNumber = 3, gender = "male", level = "base",     minPoints = 45,  recommendedPoints = 50),
    PassingScore(groupNumber = 3, gender = "male", level = "enhanced", minPoints = 95,  recommendedPoints = 105),
    PassingScore(groupNumber = 3, gender = "male", level = "special",  minPoints = 150, recommendedPoints = 165),
    // 4 группа (35–40)
    PassingScore(groupNumber = 4, gender = "male", level = "base",     minPoints = 40,  recommendedPoints = 44),
    PassingScore(groupNumber = 4, gender = "male", level = "enhanced", minPoints = 85,  recommendedPoints = 94),
    PassingScore(groupNumber = 4, gender = "male", level = "special",  minPoints = 135, recommendedPoints = 149),
    // 5 группа (40–45)
    PassingScore(groupNumber = 5, gender = "male", level = "base",     minPoints = 35,  recommendedPoints = 39),
    PassingScore(groupNumber = 5, gender = "male", level = "enhanced", minPoints = 75,  recommendedPoints = 83),
    PassingScore(groupNumber = 5, gender = "male", level = "special",  minPoints = 120, recommendedPoints = 132),
    // 6 группа (45–50)
    PassingScore(groupNumber = 6, gender = "male", level = "base",     minPoints = 30,  recommendedPoints = 33),
    PassingScore(groupNumber = 6, gender = "male", level = "enhanced", minPoints = 65,  recommendedPoints = 72),
    PassingScore(groupNumber = 6, gender = "male", level = "special",  minPoints = 105, recommendedPoints = 116),
    // 7 группа (50–55)
    PassingScore(groupNumber = 7, gender = "male", level = "base",     minPoints = 25,  recommendedPoints = 28),
    PassingScore(groupNumber = 7, gender = "male", level = "enhanced", minPoints = 55,  recommendedPoints = 61),
    PassingScore(groupNumber = 7, gender = "male", level = "special",  minPoints = 90,  recommendedPoints = 99),
    // 8 группа (55+)
    PassingScore(groupNumber = 8, gender = "male", level = "base",     minPoints = 20,  recommendedPoints = 22),
    PassingScore(groupNumber = 8, gender = "male", level = "enhanced", minPoints = 45,  recommendedPoints = 50),
    PassingScore(groupNumber = 8, gender = "male", level = "special",  minPoints = 75,  recommendedPoints = 83),

    // ═══════════════ ЖЕНЩИНЫ ═══════════════
    // 1 группа (до 25)
    PassingScore(groupNumber = 1, gender = "female", level = "base",     minPoints = 50,  recommendedPoints = 55),
    PassingScore(groupNumber = 1, gender = "female", level = "enhanced", minPoints = 100, recommendedPoints = 110),
    PassingScore(groupNumber = 1, gender = "female", level = "special",  minPoints = 150, recommendedPoints = 165),
    // 2 группа (25–30)
    PassingScore(groupNumber = 2, gender = "female", level = "base",     minPoints = 45,  recommendedPoints = 50),
    PassingScore(groupNumber = 2, gender = "female", level = "enhanced", minPoints = 90,  recommendedPoints = 99),
    PassingScore(groupNumber = 2, gender = "female", level = "special",  minPoints = 140, recommendedPoints = 154),
    // 3 группа (30–35)
    PassingScore(groupNumber = 3, gender = "female", level = "base",     minPoints = 35,  recommendedPoints = 39),
    PassingScore(groupNumber = 3, gender = "female", level = "enhanced", minPoints = 80,  recommendedPoints = 88),
    PassingScore(groupNumber = 3, gender = "female", level = "special",  minPoints = 125, recommendedPoints = 138),
    // 4 группа (35–40)
    PassingScore(groupNumber = 4, gender = "female", level = "base",     minPoints = 25,  recommendedPoints = 28),
    PassingScore(groupNumber = 4, gender = "female", level = "enhanced", minPoints = 65,  recommendedPoints = 72),
    PassingScore(groupNumber = 4, gender = "female", level = "special",  minPoints = 110, recommendedPoints = 121),
    // 5 группа (40–45)
    PassingScore(groupNumber = 5, gender = "female", level = "base",     minPoints = 20,  recommendedPoints = 22),
    PassingScore(groupNumber = 5, gender = "female", level = "enhanced", minPoints = 50,  recommendedPoints = 55),
    PassingScore(groupNumber = 5, gender = "female", level = "special",  minPoints = 80,  recommendedPoints = 88),
    // 6 группа (45–50)
    PassingScore(groupNumber = 6, gender = "female", level = "base",     minPoints = 15,  recommendedPoints = 17),
    PassingScore(groupNumber = 6, gender = "female", level = "enhanced", minPoints = 40,  recommendedPoints = 44),
    PassingScore(groupNumber = 6, gender = "female", level = "special",  minPoints = 60,  recommendedPoints = 66),
    // 7 группа (50+)
    PassingScore(groupNumber = 7, gender = "female", level = "base",     minPoints = 10,  recommendedPoints = 11),
    PassingScore(groupNumber = 7, gender = "female", level = "enhanced", minPoints = 30,  recommendedPoints = 33),
    PassingScore(groupNumber = 7, gender = "female", level = "special",  minPoints = 45,  recommendedPoints = 50)
)
