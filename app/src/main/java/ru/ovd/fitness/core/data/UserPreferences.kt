package ru.ovd.fitness.core.data

import android.content.Context
import androidx.core.content.edit

/**
 * Хранилище пользовательских настроек приложения.
 *
 * Хранит:
 *   - Пол сотрудника ("male" / "female"). По умолчанию — "male".
 *   - Возраст. По умолчанию — 30.
 *   - Уровень подготовки ("base" / "enhanced" / "special"). По умолчанию — "base".
 *   - Рост (см). По умолчанию — 175.
 *   - Вес (кг). По умолчанию — 75.
 *   - Уровень активности (1..5). По умолчанию — 3 (средний).
 *   - Флаг принятия дисклеймера рекомендаций.
 *
 * Данные сохраняются между запусками приложения
 * (в SharedPreferences).
 */
class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(
        "fitness_ovd_prefs",
        Context.MODE_PRIVATE
    )

    // ═══════════ ПОЛ ═══════════

    fun setGender(gender: String) {
        prefs.edit { putString(KEY_GENDER, gender) }
    }

    fun getGender(): String {
        return prefs.getString(KEY_GENDER, "male") ?: "male"
    }

    // ═══════════ ВОЗРАСТ ═══════════

    fun setAge(age: Int) {
        prefs.edit { putInt(KEY_AGE, age) }
    }

    fun getAge(): Int {
        return prefs.getInt(KEY_AGE, 30)
    }

    // ═══════════ УРОВЕНЬ ПОДГОТОВКИ ═══════════

    fun setLevel(level: String) {
        prefs.edit { putString(KEY_LEVEL, level) }
    }

    fun getLevel(): String {
        return prefs.getString(KEY_LEVEL, "base") ?: "base"
    }

    // ═══════════ РОСТ ═══════════

    fun setHeight(height: Int) {
        prefs.edit { putInt(KEY_HEIGHT, height) }
    }

    fun getHeight(): Int {
        return prefs.getInt(KEY_HEIGHT, 175)
    }

    // ═══════════ ВЕС ═══════════

    fun setWeight(weight: Int) {
        prefs.edit { putInt(KEY_WEIGHT, weight) }
    }

    fun getWeight(): Int {
        return prefs.getInt(KEY_WEIGHT, 75)
    }

    // ═══════════ УРОВЕНЬ АКТИВНОСТИ ═══════════
    // 1 = Очень высокий, 2 = Высокий, 3 = Средний,
    // 4 = Низкий, 5 = Очень низкий

    fun setActivityLevel(level: Int) {
        prefs.edit { putInt(KEY_ACTIVITY, level) }
    }

    fun getActivityLevel(): Int {
        return prefs.getInt(KEY_ACTIVITY, 3)
    }

    // ═══════════ ДИСКЛЕЙМЕР РЕКОМЕНДАЦИЙ ═══════════

    fun setRecommendationsDisclaimerAccepted(accepted: Boolean) {
        prefs.edit { putBoolean(KEY_DISCLAIMER, accepted) }
    }

    fun isRecommendationsDisclaimerAccepted(): Boolean {
        return prefs.getBoolean(KEY_DISCLAIMER, false)
    }

    // ═══════════ ОЧИСТКА ═══════════

    fun clear() {
        prefs.edit { clear() }
    }

    companion object {
        private const val KEY_GENDER     = "gender"
        private const val KEY_AGE        = "age"
        private const val KEY_LEVEL      = "level"
        private const val KEY_HEIGHT     = "height"
        private const val KEY_WEIGHT     = "weight"
        private const val KEY_ACTIVITY   = "activity_level"
        private const val KEY_DISCLAIMER = "recommendations_disclaimer"
    }
}
