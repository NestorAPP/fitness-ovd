package ru.ovd.fitness.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ovd.fitness.core.data.entity.AgeGroup
import ru.ovd.fitness.core.data.entity.Exercise
import ru.ovd.fitness.core.data.entity.ExerciseScore
import ru.ovd.fitness.core.data.entity.FitnessLevel
import ru.ovd.fitness.core.data.entity.PassingScore
import ru.ovd.fitness.core.data.entity.QualificationScore

@Dao
interface AppDao {

    // ═══════════ ВСТАВКА ДАННЫХ ═══════════

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgeGroups(groups: List<AgeGroup>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFitnessLevels(levels: List<FitnessLevel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassingScores(scores: List<PassingScore>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQualifications(qualifications: List<QualificationScore>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<Exercise>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseScores(scores: List<ExerciseScore>)

    // ═══════════ ПРОВЕРКА — ЕСТЬ ЛИ ДАННЫЕ ═══════════

    @Query("SELECT COUNT(*) FROM age_groups")
    suspend fun countAgeGroups(): Int

    // ═══════════ ЗАПРОСЫ ДЛЯ РАСЧЁТА ═══════════

    /** Найти возрастную группу по полу и возрасту */
    @Query("""
        SELECT * FROM age_groups
        WHERE gender = :gender
          AND :age >= minAge
          AND :age <= maxAge
        LIMIT 1
    """)
    suspend fun findAgeGroup(gender: String, age: Int): AgeGroup?

    /** Минимальный балл для сдачи итоговых занятий */
    @Query("""
        SELECT * FROM passing_scores
        WHERE gender = :gender
          AND groupNumber = :groupNumber
          AND level = :level
        LIMIT 1
    """)
    suspend fun findPassingScore(
        gender: String,
        groupNumber: Int,
        level: String
    ): PassingScore?

    /** Квалификационные звания для группы, пола, уровня */
    @Query("""
        SELECT * FROM qualification_scores
        WHERE gender = :gender
          AND groupNumber = :groupNumber
          AND level = :level
        ORDER BY qualificationOrder ASC
    """)
    suspend fun findQualifications(
        gender: String,
        groupNumber: Int,
        level: String
    ): List<QualificationScore>

    /** Упражнения для пола */
    @Query("""
        SELECT * FROM exercises
        WHERE gender = :gender
        ORDER BY orderNumber ASC
    """)
    suspend fun findExercises(gender: String): List<Exercise>

    /** Баллы за конкретное упражнение */
    @Query("""
        SELECT * FROM exercise_scores
        WHERE gender = :gender
          AND orderNumber = :orderNumber
        ORDER BY points DESC
    """)
    suspend fun findExerciseScores(
        gender: String,
        orderNumber: Int
    ): List<ExerciseScore>
}
