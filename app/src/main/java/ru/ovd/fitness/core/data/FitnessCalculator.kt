package ru.ovd.fitness.core.data

import android.content.Context
import ru.ovd.fitness.core.data.entity.QualificationScore

/**
 * Логика расчёта баллов для сотрудника.
 *
 * По возрасту и полу определяет группу, а затем подбирает
 * минимальные баллы для сдачи и квалификационные звания.
 */
class FitnessCalculator(context: Context) {

    private val repo = FitnessRepository(context)

    /**
     * Полный расчёт для сотрудника.
     *
     * @param gender   "male" / "female"
     * @param age      возраст (18..70)
     * @param level    "base" / "enhanced" / "special"
     */
    suspend fun calculate(
        gender: String,
        age: Int,
        level: String
    ): CalculationResult {

        // 1. Находим возрастную группу
        val ageGroup = repo.getAgeGroup(gender, age)
            ?: return CalculationResult.Error("Возраст вне допустимого диапазона")

        // 2. Минимальный балл для сдачи
        val passingScore = repo.getPassingScore(gender, ageGroup.groupNumber, level)

        // 3. Квалификационные звания
        val qualifications = repo.getQualifications(gender, ageGroup.groupNumber, level)

        return CalculationResult.Success(
            ageGroupLabel = ageGroup.label,
            ageGroupNumber = ageGroup.groupNumber,
            minPoints = passingScore?.minPoints ?: 0,
            recommendedPoints = passingScore?.recommendedPoints ?: 0,
            qualifications = qualifications.map {
                QualificationInfo(
                    name = it.qualificationName,
                    minPoints = it.minPoints
                )
            }
        )
    }

    /**
     * Определить, на какое звание хватает набранных баллов.
     */
    fun getReachedQualifications(
        totalPoints: Int,
        qualifications: List<QualificationInfo>
    ): List<QualificationInfo> {
        return qualifications.filter { totalPoints >= it.minPoints }
    }
}

/** Данные о звании для отображения */
data class QualificationInfo(
    val name: String,
    val minPoints: Int
)

/** Результат расчёта */
sealed class CalculationResult {
    data class Success(
        val ageGroupLabel: String,
        val ageGroupNumber: Int,
        val minPoints: Int,
        val recommendedPoints: Int,
        val qualifications: List<QualificationInfo>
    ) : CalculationResult()

    data class Error(val message: String) : CalculationResult()
}
