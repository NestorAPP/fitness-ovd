package ru.ovd.fitness.core.data

import ru.ovd.fitness.core.data.entity.QualificationScore

/**
 * Минимальные баллы для присвоения квалификационных званий.
 * Источник: приказ МВД № 44, пункт 365.3.2.
 *
 * qualificationOrder: 1 = Специалист 3 класса, 2 = Специалист 2 класса,
 *                     3 = Специалист 1 класса, 4 = Мастер.
 */
val qualificationsData: List<QualificationScore> = listOf(

    // ═══════════ БАЗОВЫЙ УРОВЕНЬ ═══════════

    // Специалист третьего класса — мужчины
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "base", groupNumber = 1, minPoints = 57),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "base", groupNumber = 2, minPoints = 52),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "base", groupNumber = 3, minPoints = 47),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "base", groupNumber = 4, minPoints = 42),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "base", groupNumber = 5, minPoints = 37),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "base", groupNumber = 6, minPoints = 32),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "base", groupNumber = 7, minPoints = 27),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "base", groupNumber = 8, minPoints = 22),
    // Специалист третьего класса — женщины
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "base", groupNumber = 1, minPoints = 54),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "base", groupNumber = 2, minPoints = 49),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "base", groupNumber = 3, minPoints = 39),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "base", groupNumber = 4, minPoints = 27),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "base", groupNumber = 5, minPoints = 22),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "base", groupNumber = 6, minPoints = 17),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "base", groupNumber = 7, minPoints = 15),

    // Специалист второго класса — мужчины
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "base", groupNumber = 1, minPoints = 59),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "base", groupNumber = 2, minPoints = 54),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "base", groupNumber = 3, minPoints = 49),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "base", groupNumber = 4, minPoints = 44),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "base", groupNumber = 5, minPoints = 39),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "base", groupNumber = 6, minPoints = 34),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "base", groupNumber = 7, minPoints = 29),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "base", groupNumber = 8, minPoints = 24),
    // Специалист второго класса — женщины
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "base", groupNumber = 1, minPoints = 58),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "base", groupNumber = 2, minPoints = 53),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "base", groupNumber = 3, minPoints = 43),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "base", groupNumber = 4, minPoints = 29),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "base", groupNumber = 5, minPoints = 24),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "base", groupNumber = 6, minPoints = 19),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "base", groupNumber = 7, minPoints = 17),

    // Специалист первого класса — мужчины
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "base", groupNumber = 1, minPoints = 62),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "base", groupNumber = 2, minPoints = 57),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "base", groupNumber = 3, minPoints = 52),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "base", groupNumber = 4, minPoints = 47),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "base", groupNumber = 5, minPoints = 42),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "base", groupNumber = 6, minPoints = 37),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "base", groupNumber = 7, minPoints = 32),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "base", groupNumber = 8, minPoints = 27),
    // Специалист первого класса — женщины
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "base", groupNumber = 1, minPoints = 64),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "base", groupNumber = 2, minPoints = 59),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "base", groupNumber = 3, minPoints = 49),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "base", groupNumber = 4, minPoints = 32),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "base", groupNumber = 5, minPoints = 27),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "base", groupNumber = 6, minPoints = 22),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "base", groupNumber = 7, minPoints = 19),

    // Мастер — мужчины
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "base", groupNumber = 1, minPoints = 65),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "base", groupNumber = 2, minPoints = 60),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "base", groupNumber = 3, minPoints = 55),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "base", groupNumber = 4, minPoints = 50),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "base", groupNumber = 5, minPoints = 45),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "base", groupNumber = 6, minPoints = 40),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "base", groupNumber = 7, minPoints = 35),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "base", groupNumber = 8, minPoints = 30),
    // Мастер — женщины
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "base", groupNumber = 1, minPoints = 70),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "base", groupNumber = 2, minPoints = 65),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "base", groupNumber = 3, minPoints = 55),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "base", groupNumber = 4, minPoints = 35),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "base", groupNumber = 5, minPoints = 30),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "base", groupNumber = 6, minPoints = 25),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "base", groupNumber = 7, minPoints = 22),

    // ═══════════ УСИЛЕННЫЙ УРОВЕНЬ ═══════════

    // Специалист третьего класса — мужчины
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "enhanced", groupNumber = 1, minPoints = 119),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "enhanced", groupNumber = 2, minPoints = 109),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "enhanced", groupNumber = 3, minPoints = 99),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "enhanced", groupNumber = 4, minPoints = 89),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "enhanced", groupNumber = 5, minPoints = 79),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "enhanced", groupNumber = 6, minPoints = 69),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "enhanced", groupNumber = 7, minPoints = 59),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "enhanced", groupNumber = 8, minPoints = 49),
    // Специалист третьего класса — женщины
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "enhanced", groupNumber = 1, minPoints = 104),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "enhanced", groupNumber = 2, minPoints = 94),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "enhanced", groupNumber = 3, minPoints = 84),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "enhanced", groupNumber = 4, minPoints = 69),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "enhanced", groupNumber = 5, minPoints = 54),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "enhanced", groupNumber = 6, minPoints = 44),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "enhanced", groupNumber = 7, minPoints = 40),

    // Специалист второго класса — мужчины
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "enhanced", groupNumber = 1, minPoints = 123),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "enhanced", groupNumber = 2, minPoints = 113),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "enhanced", groupNumber = 3, minPoints = 103),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "enhanced", groupNumber = 4, minPoints = 93),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "enhanced", groupNumber = 5, minPoints = 83),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "enhanced", groupNumber = 6, minPoints = 73),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "enhanced", groupNumber = 7, minPoints = 63),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "enhanced", groupNumber = 8, minPoints = 53),
    // Специалист второго класса — женщины
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "enhanced", groupNumber = 1, minPoints = 108),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "enhanced", groupNumber = 2, minPoints = 98),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "enhanced", groupNumber = 3, minPoints = 88),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "enhanced", groupNumber = 4, minPoints = 73),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "enhanced", groupNumber = 5, minPoints = 58),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "enhanced", groupNumber = 6, minPoints = 48),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "enhanced", groupNumber = 7, minPoints = 44),

    // Специалист первого класса — мужчины
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "enhanced", groupNumber = 1, minPoints = 129),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "enhanced", groupNumber = 2, minPoints = 119),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "enhanced", groupNumber = 3, minPoints = 109),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "enhanced", groupNumber = 4, minPoints = 99),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "enhanced", groupNumber = 5, minPoints = 89),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "enhanced", groupNumber = 6, minPoints = 79),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "enhanced", groupNumber = 7, minPoints = 69),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "enhanced", groupNumber = 8, minPoints = 59),
    // Специалист первого класса — женщины
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "enhanced", groupNumber = 1, minPoints = 114),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "enhanced", groupNumber = 2, minPoints = 104),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "enhanced", groupNumber = 3, minPoints = 94),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "enhanced", groupNumber = 4, minPoints = 79),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "enhanced", groupNumber = 5, minPoints = 64),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "enhanced", groupNumber = 6, minPoints = 54),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "enhanced", groupNumber = 7, minPoints = 48),

    // Мастер — мужчины
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "enhanced", groupNumber = 1, minPoints = 135),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "enhanced", groupNumber = 2, minPoints = 125),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "enhanced", groupNumber = 3, minPoints = 115),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "enhanced", groupNumber = 4, minPoints = 105),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "enhanced", groupNumber = 5, minPoints = 95),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "enhanced", groupNumber = 6, minPoints = 85),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "enhanced", groupNumber = 7, minPoints = 75),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "enhanced", groupNumber = 8, minPoints = 65),
    // Мастер — женщины
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "enhanced", groupNumber = 1, minPoints = 120),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "enhanced", groupNumber = 2, minPoints = 110),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "enhanced", groupNumber = 3, minPoints = 100),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "enhanced", groupNumber = 4, minPoints = 85),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "enhanced", groupNumber = 5, minPoints = 70),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "enhanced", groupNumber = 6, minPoints = 60),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "enhanced", groupNumber = 7, minPoints = 54),

    // ═══════════ СПЕЦИАЛЬНЫЙ УРОВЕНЬ ═══════════

    // Специалист третьего класса — мужчины
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "special", groupNumber = 1, minPoints = 185),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "special", groupNumber = 2, minPoints = 170),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "special", groupNumber = 3, minPoints = 155),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "special", groupNumber = 4, minPoints = 140),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "special", groupNumber = 5, minPoints = 125),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "special", groupNumber = 6, minPoints = 110),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "special", groupNumber = 7, minPoints = 95),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "male", level = "special", groupNumber = 8, minPoints = 80),
    // Специалист третьего класса — женщины
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "special", groupNumber = 1, minPoints = 155),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "special", groupNumber = 2, minPoints = 145),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "special", groupNumber = 3, minPoints = 130),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "special", groupNumber = 4, minPoints = 115),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "special", groupNumber = 5, minPoints = 85),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "special", groupNumber = 6, minPoints = 65),
    QualificationScore(qualificationName = "Специалист третьего класса", qualificationOrder = 1, gender = "female", level = "special", groupNumber = 7, minPoints = 60),

    // Специалист второго класса — мужчины
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "special", groupNumber = 1, minPoints = 190),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "special", groupNumber = 2, minPoints = 175),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "special", groupNumber = 3, minPoints = 160),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "special", groupNumber = 4, minPoints = 145),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "special", groupNumber = 5, minPoints = 130),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "special", groupNumber = 6, minPoints = 115),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "special", groupNumber = 7, minPoints = 100),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "male", level = "special", groupNumber = 8, minPoints = 85),
    // Специалист второго класса — женщины
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "special", groupNumber = 1, minPoints = 160),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "special", groupNumber = 2, minPoints = 150),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "special", groupNumber = 3, minPoints = 135),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "special", groupNumber = 4, minPoints = 120),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "special", groupNumber = 5, minPoints = 90),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "special", groupNumber = 6, minPoints = 70),
    QualificationScore(qualificationName = "Специалист второго класса", qualificationOrder = 2, gender = "female", level = "special", groupNumber = 7, minPoints = 65),

    // Специалист первого класса — мужчины
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "special", groupNumber = 1, minPoints = 195),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "special", groupNumber = 2, minPoints = 180),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "special", groupNumber = 3, minPoints = 165),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "special", groupNumber = 4, minPoints = 150),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "special", groupNumber = 5, minPoints = 135),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "special", groupNumber = 6, minPoints = 120),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "special", groupNumber = 7, minPoints = 105),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "male", level = "special", groupNumber = 8, minPoints = 90),
    // Специалист первого класса — женщины
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "special", groupNumber = 1, minPoints = 165),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "special", groupNumber = 2, minPoints = 155),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "special", groupNumber = 3, minPoints = 140),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "special", groupNumber = 4, minPoints = 125),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "special", groupNumber = 5, minPoints = 95),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "special", groupNumber = 6, minPoints = 75),
    QualificationScore(qualificationName = "Специалист первого класса", qualificationOrder = 3, gender = "female", level = "special", groupNumber = 7, minPoints = 70),

    // Мастер — мужчины
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "special", groupNumber = 1, minPoints = 200),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "special", groupNumber = 2, minPoints = 185),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "special", groupNumber = 3, minPoints = 170),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "special", groupNumber = 4, minPoints = 155),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "special", groupNumber = 5, minPoints = 140),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "special", groupNumber = 6, minPoints = 125),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "special", groupNumber = 7, minPoints = 110),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "male", level = "special", groupNumber = 8, minPoints = 95),
    // Мастер — женщины
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "special", groupNumber = 1, minPoints = 170),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "special", groupNumber = 2, minPoints = 160),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "special", groupNumber = 3, minPoints = 145),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "special", groupNumber = 4, minPoints = 130),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "special", groupNumber = 5, minPoints = 100),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "special", groupNumber = 6, minPoints = 80),
    QualificationScore(qualificationName = "Мастер", qualificationOrder = 4, gender = "female", level = "special", groupNumber = 7, minPoints = 75)
)
