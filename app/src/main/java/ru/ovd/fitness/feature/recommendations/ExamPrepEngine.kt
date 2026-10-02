package ru.ovd.fitness.feature.recommendations

import ru.ovd.fitness.core.data.entity.ExerciseScore

/**
 * Фаза подготовки.
 */
data class PrepPhase(
    val title: String,
    val subtitle: String,
    val durationDays: Int,
    val items: List<String>
)

/**
 * Задача по конкретному упражнению.
 */
data class ExerciseGoal(
    val exerciseName: String,
    val category: String,
    val unit: String,
    val currentResult: String,
    val targetResult: String,
    val targetPoints: Int
)

/**
 * Полный результат программы подготовки.
 */
data class ExamPrepResult(
    val daysLeft: Int,
    val tooShort: Boolean,
    val warning: String?,
    val totalTargetPoints: Int,
    val exerciseGoals: List<ExerciseGoal>,
    val phases: List<PrepPhase>
)

/**
 * Генератор программы подготовки к итоговым занятиям.
 *
 * Логика:
 * - Мало времени (< 14 дней) → только интенсивная.
 * - Средне (14–30 дней) → 2 фазы.
 * - Много (> 30 дней) → 3 фазы.
 *
 * Возраст влияет на интенсивность.
 */
object ExamPrepEngine {

    fun generate(
        age: Int,
        level: String,
        daysLeft: Int,
        totalTargetPoints: Int,
        exercises: List<SelectedExercise>,
        scoreLists: Map<Int, List<ExerciseScore>>
    ): ExamPrepResult {

        // ─── Проверка на слишком малое время ───
        val tooShort = daysLeft < 14
        val warning = if (tooShort) {
            "До сдачи осталось мало времени. Программа интенсивная, но может не хватить времени на полную подготовку."
        } else null

        // ─── Считаем цели по упражнениям ───
        val pointsPerExercise = if (exercises.isNotEmpty())
            totalTargetPoints / exercises.size
        else 0

        val goals = exercises.map { ex ->
            val scores = scoreLists[ex.orderNumber].orEmpty()
            val targetDisplay = findTargetResult(scores, pointsPerExercise)

            ExerciseGoal(
                exerciseName = ex.name,
                category = ex.category,
                unit = ex.unit,
                currentResult = ex.currentResult,
                targetResult = targetDisplay,
                targetPoints = pointsPerExercise
            )
        }

        // ─── Строим фазы ───
        val phases = buildPhases(
            age = age,
            daysLeft = daysLeft,
            exercises = exercises,
            level = level
        )

        return ExamPrepResult(
            daysLeft = daysLeft,
            tooShort = tooShort,
            warning = warning,
            totalTargetPoints = totalTargetPoints,
            exerciseGoals = goals,
            phases = phases
        )
    }

    /**
     * Находит результат, соответствующий нужному количеству баллов.
     *
     * Логика: берём МИНИМАЛЬНЫЙ балл, который >= targetPoints.
     * Например, если цель = 45, и в базе есть 45, 46, 47 — берём 45.
     * Если 45 нет, но есть 45,5 — берём 46.
     */
    private fun findTargetResult(
        scores: List<ExerciseScore>,
        targetPoints: Int
    ): String {
        if (scores.isEmpty()) return "—"

        // Ищем минимальный балл, который >= targetPoints
        val candidate = scores
            .filter { it.points >= targetPoints }
            .minByOrNull { it.points }
            ?: scores.maxByOrNull { it.points }

        return candidate?.resultDisplay ?: "—"
    }

    /**
     * Строит фазы программы.
     */
    private fun buildPhases(
        age: Int,
        daysLeft: Int,
        exercises: List<SelectedExercise>,
        level: String
    ): List<PrepPhase> {

        // ─── Коэффициент по возрасту ───
        val ageFactor = when {
            age < 30 -> 1.0
            age < 40 -> 0.9
            age < 45 -> 0.8
            age < 50 -> 0.7
            else     -> 0.6
        }

        // ─── Базовые числа для силовых ───
        val strengthBase = (10 * ageFactor).toInt().coerceAtLeast(4)

        val phases = mutableListOf<PrepPhase>()

        if (daysLeft < 14) {
            // ─── Только интенсивная фаза ───
            phases.add(
                PrepPhase(
                    title = "Интенсивная подготовка",
                    subtitle = "$daysLeft дней · ежедневно",
                    durationDays = daysLeft,
                    items = buildIntensiveItems(exercises, strengthBase)
                )
            )
        } else if (daysLeft < 30) {
            // ─── Две фазы ───
            val split = daysLeft / 2

            phases.add(
                PrepPhase(
                    title = "Фаза 1: Развивающая",
                    subtitle = "$split дней · 3–4 раза в неделю",
                    durationDays = split,
                    items = buildDevelopmentItems(exercises, strengthBase)
                )
            )
            phases.add(
                PrepPhase(
                    title = "Фаза 2: Подводящая",
                    subtitle = "${daysLeft - split} дней · 2–3 раза в неделю",
                    durationDays = daysLeft - split,
                    items = buildTaperItems(exercises, strengthBase)
                )
            )
        } else {
            // ─── Три фазы ───
            val base = (daysLeft * 0.4).toInt()
            val develop = (daysLeft * 0.4).toInt()
            val taper = daysLeft - base - develop

            phases.add(
                PrepPhase(
                    title = "Фаза 1: Базовая",
                    subtitle = "$base дней · 3 раза в неделю",
                    durationDays = base,
                    items = buildBaseItems(exercises, strengthBase)
                )
            )
            phases.add(
                PrepPhase(
                    title = "Фаза 2: Развивающая",
                    subtitle = "$develop дней · 3–4 раза в неделю",
                    durationDays = develop,
                    items = buildDevelopmentItems(exercises, strengthBase)
                )
            )
            phases.add(
                PrepPhase(
                    title = "Фаза 3: Подводящая",
                    subtitle = "$taper дней · 2 раза в неделю + отдых",
                    durationDays = taper,
                    items = buildTaperItems(exercises, strengthBase)
                )
            )
        }

        return phases
    }

    // ═══════════════════════════════════════════
    //   БЛОКИ УПРАЖНЕНИЙ
    // ═══════════════════════════════════════════

    private fun buildBaseItems(
        exercises: List<SelectedExercise>,
        base: Int
    ): List<String> {
        val items = mutableListOf<String>()

        exercises.forEach { ex ->
            when (ex.category) {
                "Сила" -> items.add("${ex.name}: 3 подхода × ${(base * 0.6).toInt()}")
                "Быстрота и ловкость" -> items.add("${ex.name}: 4–5 ускорений")
                "Выносливость" -> items.add("${ex.name}: 15–20 минут в лёгком темпе")
            }
        }

        items.add("Разминка 5–7 минут перед каждым занятием")
        items.add("Растяжка 5–10 минут после")

        return items
    }

    private fun buildDevelopmentItems(
        exercises: List<SelectedExercise>,
        base: Int
    ): List<String> {
        val items = mutableListOf<String>()

        exercises.forEach { ex ->
            when (ex.category) {
                "Сила" -> items.add("${ex.name}: 4 подхода × $base")
                "Быстрота и ловкость" -> items.add("${ex.name}: 6–8 ускорений + техника")
                "Выносливость" -> items.add("${ex.name}: 25–30 минут в среднем темпе")
            }
        }

        items.add("Разминка 5–7 минут")
        items.add("Растяжка 5–10 минут")

        return items
    }

    private fun buildTaperItems(
        exercises: List<SelectedExercise>,
        base: Int
    ): List<String> {
        val items = mutableListOf<String>()

        exercises.forEach { ex ->
            when (ex.category) {
                "Сила" -> items.add("${ex.name}: 2 подхода × ${(base * 0.7).toInt()}")
                "Быстрота и ловкость" -> items.add("${ex.name}: 3–4 ускорения")
                "Выносливость" -> items.add("${ex.name}: 15 минут лёгкого бега")
            }
        }

        items.add("За 3 дня до сдачи — только лёгкая разминка")
        items.add("Сон 8 часов, питание обычное")

        return items
    }

    private fun buildIntensiveItems(
        exercises: List<SelectedExercise>,
        base: Int
    ): List<String> {
        val items = mutableListOf<String>()

        exercises.forEach { ex ->
            when (ex.category) {
                "Сила" -> items.add("${ex.name}: 4 подхода × ${(base * 1.2).toInt()}, ежедневно")
                "Быстрота и ловкость" -> items.add("${ex.name}: 6 ускорений, ежедневно")
                "Выносливость" -> items.add("${ex.name}: 20–25 минут, ежедневно")
            }
        }

        items.add("⚠️ Не перетренируйтесь — обязательно 1 день отдыха в неделю")
        items.add("Разминка обязательна")

        return items
    }
}
