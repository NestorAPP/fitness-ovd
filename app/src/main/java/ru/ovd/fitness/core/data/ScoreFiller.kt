package ru.ovd.fitness.core.data

import ru.ovd.fitness.core.data.entity.ExerciseScore

/**
 * Утилита для заполнения пропусков в баллах.
 *
 * В приказе есть строки, где за результат балл не даётся (прочерк).
 * Логика: для прочерка ставим ближайший меньший балл
 * (то есть балл, который идёт следующим в таблице).
 *
 * ВАЖНО: "ближайший меньший" — по числу баллов, а не по результату.
 *
 * Пример (подтягивание, мужчины):
 *   22 → 100
 *   21 → 98
 *   20 → 93
 *   19 → — → заменяем на 88 (потому что 88 < 93)
 *   18 → 88
 */
object ScoreFiller {

    /**
     * Заполнить пропуски (прочерки) баллами.
     *
     * @param raw список ExerciseScore, отсортированный по баллам (100 → 0)
     * @return новый список, где прочерки заменены на ближайший меньший балл
     */
    fun fill(raw: List<ExerciseScore>): List<ExerciseScore> {
        if (raw.isEmpty()) return raw

        // Идём ОТ МЕНЬШЕГО к большему (0 → 100)
        // и запоминаем последний встреченный балл.
        // Потом возвращаемся в исходный порядок.
        val fromBottom = raw.sortedBy { it.points }

        val filled = mutableListOf<ExerciseScore>()
        var lastSeen = 0

        for (score in fromBottom) {
            if (score.resultMinSec == null && score.resultMaxSec == null) {
                // Прочерк — берём ближайший меньший балл (тот, что был последним)
                filled.add(score.copy(points = lastSeen))
            } else {
                lastSeen = score.points
                filled.add(score)
            }
        }

        // Возвращаем в исходный порядок (от большого к меньшему)
        return filled.sortedByDescending { it.points }
    }
}
