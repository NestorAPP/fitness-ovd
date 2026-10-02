package ru.ovd.fitness.core.data

import android.content.Context
import androidx.core.content.edit

class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(
        "fitness_ovd_prefs",
        Context.MODE_PRIVATE
    )

    // ═══════════ ПОЛ ═══════════
    fun setGender(gender: String) { prefs.edit { putString(KEY_GENDER, gender) } }
    fun getGender(): String = prefs.getString(KEY_GENDER, "male") ?: "male"

    // ═══════════ ВОЗРАСТ ═══════════
    fun setAge(age: Int) { prefs.edit { putInt(KEY_AGE, age) } }
    fun getAge(): Int = prefs.getInt(KEY_AGE, 30)

    // ═══════════ УРОВЕНЬ ═══════════
    fun setLevel(level: String) { prefs.edit { putString(KEY_LEVEL, level) } }
    fun getLevel(): String = prefs.getString(KEY_LEVEL, "base") ?: "base"

    // ═══════════ РОСТ ═══════════
    fun setHeight(height: Int) { prefs.edit { putInt(KEY_HEIGHT, height) } }
    fun getHeight(): Int = prefs.getInt(KEY_HEIGHT, 175)

    // ═══════════ ВЕС ═══════════
    fun setWeight(weight: Int) { prefs.edit { putInt(KEY_WEIGHT, weight) } }
    fun getWeight(): Int = prefs.getInt(KEY_WEIGHT, 75)

    // ═══════════ АКТИВНОСТЬ ═══════════
    fun setActivityLevel(level: Int) { prefs.edit { putInt(KEY_ACTIVITY, level) } }
    fun getActivityLevel(): Int = prefs.getInt(KEY_ACTIVITY, 3)

    // ═══════════ ДИСКЛЕЙМЕР ═══════════
    fun setRecommendationsDisclaimerAccepted(accepted: Boolean) {
        prefs.edit { putBoolean(KEY_DISCLAIMER, accepted) }
    }
    fun isRecommendationsDisclaimerAccepted(): Boolean =
        prefs.getBoolean(KEY_DISCLAIMER, false)

    // ═══════════ ПОДГОТОВКА К ИТОГОВЫМ ═══════════
    fun setExamType(type: String) { prefs.edit { putString(KEY_EXAM_TYPE, type) } }
    fun getExamType(): String = prefs.getString(KEY_EXAM_TYPE, "FINAL") ?: "FINAL"

    fun setExamDate(millis: Long) { prefs.edit { putLong(KEY_EXAM_DATE, millis) } }
    fun getExamDate(): Long = prefs.getLong(KEY_EXAM_DATE, 0L)

    fun setQualificationName(name: String?) {
        prefs.edit { putString(KEY_QUALIFICATION, name ?: "") }
    }
    fun getQualificationName(): String? {
        val v = prefs.getString(KEY_QUALIFICATION, "") ?: ""
        return v.ifBlank { null }
    }

    fun setSelectedExercises(serialized: String) {
        prefs.edit { putString(KEY_EXERCISES, serialized) }
    }
    fun getSelectedExercises(): String = prefs.getString(KEY_EXERCISES, "") ?: ""

    companion object {
        private const val KEY_GENDER      = "gender"
        private const val KEY_AGE         = "age"
        private const val KEY_LEVEL       = "level"
        private const val KEY_HEIGHT      = "height"
        private const val KEY_WEIGHT      = "weight"
        private const val KEY_ACTIVITY    = "activity_level"
        private const val KEY_DISCLAIMER  = "recommendations_disclaimer"

        private const val KEY_EXAM_TYPE     = "exam_type"
        private const val KEY_EXAM_DATE     = "exam_date"
        private const val KEY_QUALIFICATION = "exam_qualification"
        private const val KEY_EXERCISES     = "exam_exercises"
    }
}
