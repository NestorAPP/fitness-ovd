package ru.ovd.fitness.feature.recommendations

/**
 * Блок рекомендаций — одна логическая группа упражнений.
 */
data class RecommendationBlock(
    val title: String,
    val subtitle: String,
    val items: List<String>
)

/**
 * Категория ИМТ.
 */
enum class BmiCategory(val label: String) {
    UNDERWEIGHT("Недостаточный вес"),
    NORMAL("Норма"),
    OVERWEIGHT("Избыточный вес"),
    OBESITY_I("Ожирение I степени"),
    OBESITY_II("Ожирение II степени"),
    OBESITY_III("Ожирение III степени")
}

/**
 * Результат формирования рекомендаций.
 */
data class RecommendationResult(
    val bmi: Double,
    val bmiCategory: BmiCategory,
    val warning: String?,
    val blocks: List<RecommendationBlock>
)

/**
 * Логика формирования рекомендаций.
 *
 * Главный принцип: «Не навреди».
 *
 * - При избыточном весе — исключаем ударные нагрузки (бег).
 * - При ожирении — только ходьба и плавание.
 * - При низкой активности — начинаем с минимума.
 * - При возрасте 45+ — снижаем интенсивность.
 */
object RecommendationEngine {

    fun generate(
        gender: String,
        age: Int,
        heightCm: Int,
        weightKg: Int,
        activityLevel: Int
    ): RecommendationResult {

        // ─── ИМТ ───
        val heightM = heightCm / 100.0
        val bmi = weightKg / (heightM * heightM)
        val category = categoryOf(bmi)

        // ─── Предупреждение ───
        val warning = warningFor(category)

        // ─── Блоки ───
        val blocks = mutableListOf<RecommendationBlock>()

        // 1. Утренняя зарядка (ежедневно) — для всех
        blocks.add(morningRoutine(gender, age, category, activityLevel))

        // 2. Кардио — с учётом ИМТ
        blocks.add(cardioBlock(category, activityLevel))

        // 3. Силовая — 2–3 раза в неделю
        blocks.add(strengthBlock(gender, age, category, activityLevel))

        // 4. Общие принципы
        blocks.add(generalPrinciples())

        return RecommendationResult(
            bmi = bmi,
            bmiCategory = category,
            warning = warning,
            blocks = blocks
        )
    }

    // ═══════════════════════════════════════════
    //   ИМТ
    // ═══════════════════════════════════════════

    private fun categoryOf(bmi: Double): BmiCategory = when {
        bmi < 18.5 -> BmiCategory.UNDERWEIGHT
        bmi < 25.0 -> BmiCategory.NORMAL
        bmi < 30.0 -> BmiCategory.OVERWEIGHT
        bmi < 35.0 -> BmiCategory.OBESITY_I
        bmi < 40.0 -> BmiCategory.OBESITY_II
        else       -> BmiCategory.OBESITY_III
    }

    private fun warningFor(category: BmiCategory): String? = when (category) {
        BmiCategory.UNDERWEIGHT ->
            "У вас недостаточный вес. Рекомендуем проконсультироваться с врачом и диетологом перед началом тренировок."

        BmiCategory.OBESITY_I ->
            "У вас избыточный вес. Бег и прыжковые упражнения исключены — нагрузка на суставы слишком велика. Начинайте с ходьбы."

        BmiCategory.OBESITY_II ->
            "У вас выраженный избыточный вес. Начинайте только с ходьбы и плавания. Обязательно проконсультируйтесь с врачом перед началом занятий."

        BmiCategory.OBESITY_III ->
            "У вас значительный избыточный вес. Начинайте ТОЛЬКО с ходьбы. Бег, прыжки и силовые упражнения с высокой нагрузкой исключены. Обязательно проконсультируйтесь с врачом."

        else -> null
    }

    // ═══════════════════════════════════════════
    //   БЛОК 1: УТРЕННЯЯ ЗАРЯДКА
    // ═══════════════════════════════════════════

    private fun morningRoutine(
        gender: String,
        age: Int,
        category: BmiCategory,
        activityLevel: Int
    ): RecommendationBlock {

        // Базовые повторы — зависят от возраста, активности и ИМТ
        val baseCount = when {
            activityLevel == 1 -> 20
            activityLevel == 2 -> 15
            activityLevel == 3 -> 12
            activityLevel == 4 -> 8
            else                -> 6
        }

        val ageFactor = when {
            age < 30 -> 1.0
            age < 40 -> 0.9
            age < 50 -> 0.8
            age < 60 -> 0.7
            else     -> 0.6
        }

        val bmiFactor = when (category) {
            BmiCategory.UNDERWEIGHT -> 0.8
            BmiCategory.NORMAL      -> 1.0
            BmiCategory.OVERWEIGHT  -> 0.9
            BmiCategory.OBESITY_I   -> 0.7
            BmiCategory.OBESITY_II  -> 0.5
            BmiCategory.OBESITY_III -> 0.4
        }

        val pushups = (baseCount * ageFactor * bmiFactor).toInt().coerceAtLeast(3)
        val squats  = (baseCount * ageFactor * bmiFactor * 1.5).toInt().coerceAtLeast(5)
        val plank   = (baseCount * ageFactor).toInt().coerceIn(15, 60)
        val abs     = (baseCount * ageFactor * bmiFactor).toInt().coerceAtLeast(5)

        val items = mutableListOf<String>()

        if (gender == "male") {
            items.add("Отжимания: 2 подхода × $pushups")
        } else {
            items.add("Отжимания (можно с колен): 2 подхода × $pushups")
        }

        items.add("Приседания: 2 подхода × $squats")
        items.add("Планка: 2 подхода × $plank секунд")
        items.add("Пресс (скручивания): 2 подхода × $abs")
        items.add("Растяжка: 5 минут")

        return RecommendationBlock(
            title = "Утренняя зарядка",
            subtitle = "Ежедневно, 10–15 минут",
            items = items
        )
    }

    // ═══════════════════════════════════════════
    //   БЛОК 2: КАРДИО
    // ═══════════════════════════════════════════

    private fun cardioBlock(
        category: BmiCategory,
        activityLevel: Int
    ): RecommendationBlock {

        val items = mutableListOf<String>()

        when (category) {
            BmiCategory.UNDERWEIGHT,
            BmiCategory.NORMAL -> {
                items.add("Бег трусцой: 20–30 минут")
                items.add("или ходьба быстрым шагом: 40–60 минут")
                items.add("или велосипед: 30–45 минут")
                items.add("или плавание: 30 минут")
            }

            BmiCategory.OVERWEIGHT -> {
                items.add("⚠ Бег не рекомендуем — большая нагрузка на суставы")
                items.add("Ходьба быстрым шагом: 40–60 минут")
                items.add("или велосипед: 30 минут")
                items.add("или плавание: 30 минут")
            }

            BmiCategory.OBESITY_I,
            BmiCategory.OBESITY_II -> {
                items.add("⚠ Только ходьба и плавание")
                items.add("Ходьба: 30–40 минут в комфортном темпе")
                items.add("или плавание: 20–30 минут")
            }

            BmiCategory.OBESITY_III -> {
                items.add("⚠ Только ходьба")
                items.add("Ходьба: 20–30 минут в медленном темпе")
                items.add("Обязательна консультация врача перед началом")
            }
        }

        val frequency = when (activityLevel) {
            1, 2 -> "3–4 раза в неделю"
            3    -> "2–3 раза в неделю"
            4    -> "2 раза в неделю"
            else -> "Начните с 1–2 раз в неделю"
        }

        return RecommendationBlock(
            title = "Кардио",
            subtitle = frequency,
            items = items
        )
    }

    // ═══════════════════════════════════════════
    //   БЛОК 3: СИЛОВАЯ
    // ═══════════════════════════════════════════

    private fun strengthBlock(
        gender: String,
        age: Int,
        category: BmiCategory,
        activityLevel: Int
    ): RecommendationBlock {

        val baseCount = when {
            activityLevel == 1 -> 15
            activityLevel == 2 -> 12
            activityLevel == 3 -> 10
            activityLevel == 4 -> 8
            else                -> 6
        }

        val ageFactor = when {
            age < 30 -> 1.0
            age < 40 -> 0.9
            age < 50 -> 0.8
            else     -> 0.7
        }

        val bmiFactor = when (category) {
            BmiCategory.UNDERWEIGHT -> 0.7
            BmiCategory.NORMAL      -> 1.0
            BmiCategory.OVERWEIGHT  -> 0.9
            BmiCategory.OBESITY_I   -> 0.6
            BmiCategory.OBESITY_II  -> 0.4
            BmiCategory.OBESITY_III -> 0.3
        }

        val pushups = (baseCount * ageFactor * bmiFactor).toInt().coerceAtLeast(3)
        val squats  = (baseCount * ageFactor * bmiFactor * 1.5).toInt().coerceAtLeast(5)
        val plank   = (baseCount * ageFactor).toInt().coerceIn(20, 60)
        val abs     = (baseCount * ageFactor * bmiFactor).toInt().coerceAtLeast(5)

        val items = mutableListOf<String>()

        if (gender == "male") {
            items.add("Отжимания: 3 подхода × $pushups")
            items.add("Приседания: 3 подхода × $squats")
        } else {
            items.add("Отжимания (можно с колен): 3 подхода × $pushups")
            items.add("Приседания: 3 подхода × $squats")
        }

        items.add("Планка: 3 подхода × $plank секунд")
        items.add("Пресс: 3 подхода × $abs")

        if (gender == "male" && activityLevel <= 2 && category == BmiCategory.NORMAL) {
            items.add("Подтягивания: 3 подхода × максимум")
        }

        return RecommendationBlock(
            title = "Силовая тренировка",
            subtitle = "2–3 раза в неделю, с отдыхом 1 день между занятиями",
            items = items
        )
    }

    // ═══════════════════════════════════════════
    //   БЛОК 4: ОБЩИЕ ПРИНЦИПЫ
    // ═══════════════════════════════════════════

    private fun generalPrinciples(): RecommendationBlock {
        return RecommendationBlock(
            title = "Общие принципы",
            subtitle = "Важно соблюдать",
            items = listOf(
                "Разминка 5–7 минут перед каждой тренировкой",
                "Растяжка 5–10 минут после тренировки",
                "Сон 7–8 часов — основа восстановления",
                "Пейте воду: 1,5–2 литра в день",
                "Не тренируйтесь сразу после еды — подождите 1,5–2 часа",
                "При боли или дискомфорте — прекратите и отдохните",
                "Увеличение нагрузки — не более 10% в неделю"
            )
        )
    }
}
