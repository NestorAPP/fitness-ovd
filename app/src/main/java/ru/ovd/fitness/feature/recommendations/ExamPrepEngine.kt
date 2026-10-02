private fun findTargetResult(
    scores: List<ExerciseScore>,
    targetPoints: Int
): String {
    if (scores.isEmpty()) return "—"

    // Ищем ближайшее значение с баллами >= targetPoints
    val sorted = scores.sortedByDescending { it.points }
    val candidate = sorted.firstOrNull { it.points >= targetPoints }
        ?: sorted.lastOrNull()

    return candidate?.resultDisplay ?: "—"
}
