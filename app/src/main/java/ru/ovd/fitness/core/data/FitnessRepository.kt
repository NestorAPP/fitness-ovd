package ru.ovd.fitness.core.data

import android.content.Context
import ru.ovd.fitness.core.data.entity.AgeGroup
import ru.ovd.fitness.core.data.entity.Exercise
import ru.ovd.fitness.core.data.entity.ExerciseScore
import ru.ovd.fitness.core.data.entity.PassingScore
import ru.ovd.fitness.core.data.entity.QualificationScore

/**
 * Обёртка над базой данных. Все запросы идут через неё.
 *
 * Упрощает доступ: UI не знает про Room, а получает готовые данные.
 */
class FitnessRepository(private val context: Context) {

    private val dao = AppDatabase.getDatabase(context).dao()

    /** Найти возрастную группу по полу и возрасту */
    suspend fun getAgeGroup(gender: String, age: Int): AgeGroup? {
        return dao.findAgeGroup(gender, age)
    }

    /** Минимальный балл для сдачи */
    suspend fun getPassingScore(
        gender: String,
        groupNumber: Int,
        level: String
    ): PassingScore? {
        return dao.findPassingScore(gender, groupNumber, level)
    }

    /** Квалификационные звания */
    suspend fun getQualifications(
        gender: String,
        groupNumber: Int,
        level: String
    ): List<QualificationScore> {
        return dao.findQualifications(gender, groupNumber, level)
    }

    /** Упражнения для пола */
    suspend fun getExercises(gender: String): List<Exercise> {
        return dao.findExercises(gender)
    }

    /** Баллы за конкретное упражнение */
    suspend fun getExerciseScores(
        gender: String,
        orderNumber: Int
    ): List<ExerciseScore> {
        return dao.findExerciseScores(gender, orderNumber)
    }
}
