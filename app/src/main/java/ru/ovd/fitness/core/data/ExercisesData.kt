package ru.ovd.fitness.core.data

import ru.ovd.fitness.core.data.entity.Exercise

/**
 * Названия контрольных упражнений (приказ МВД № 44, пункт 343).
 *
 * Упражнения для мужчин — 9 штук, для женщин — 7 штук.
 */
val exercisesData: List<Exercise> = listOf(

    // ═══════════════ МУЖЧИНЫ ═══════════════
    Exercise(gender = "male", orderNumber = 1, name = "Подтягивание на перекладине", unit = "раз", category = "Сила"),
    Exercise(gender = "male", orderNumber = 2, name = "Жим гири весом 24 килограмма", unit = "раз", category = "Сила"),
    Exercise(gender = "male", orderNumber = 3, name = "Сгибание и разгибание рук в упоре лёжа", unit = "раз", category = "Сила"),
    Exercise(gender = "male", orderNumber = 4, name = "Челночный бег 10×10 метров", unit = "секунд", category = "Быстрота и ловкость"),
    Exercise(gender = "male", orderNumber = 5, name = "Челночный бег 4×20 метров", unit = "секунд", category = "Быстрота и ловкость"),
    Exercise(gender = "male", orderNumber = 6, name = "Бег (кросс) на 3 километра", unit = "минут, секунд", category = "Выносливость"),
    Exercise(gender = "male", orderNumber = 7, name = "Бег (кросс) на 5 километров", unit = "минут, секунд", category = "Выносливость"),
    Exercise(gender = "male", orderNumber = 8, name = "Бег на лыжах на 5 километров", unit = "минут, секунд", category = "Выносливость"),
    Exercise(gender = "male", orderNumber = 9, name = "Плавание на 100 метров", unit = "минут, секунд", category = "Выносливость"),

    // ═══════════════ ЖЕНЩИНЫ ═══════════════
    Exercise(gender = "female", orderNumber = 1, name = "Сгибание и разгибание рук в упоре лёжа", unit = "раз", category = "Сила"),
    Exercise(gender = "female", orderNumber = 2, name = "Наклоны вперёд из положения лёжа на спине в течение одной минуты", unit = "раз", category = "Сила"),
    Exercise(gender = "female", orderNumber = 3, name = "Челночный бег 10×10 метров", unit = "секунд", category = "Быстрота и ловкость"),
    Exercise(gender = "female", orderNumber = 4, name = "Бег (кросс) на 1 километр", unit = "минут, секунд", category = "Выносливость"),
    Exercise(gender = "female", orderNumber = 5, name = "Бег (кросс) на 3 километра", unit = "минут, секунд", category = "Выносливость"),
    Exercise(gender = "female", orderNumber = 6, name = "Бег на лыжах на 5 километров", unit = "минут, секунд", category = "Выносливость"),
    Exercise(gender = "female", orderNumber = 7, name = "Плавание на 100 метров", unit = "минут, секунд", category = "Выносливость")
)
