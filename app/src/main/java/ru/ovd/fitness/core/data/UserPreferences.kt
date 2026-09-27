package ru.ovd.fitness.core.data

import android.content.Context
import androidx.core.content.edit

/**
 * Хранилище пользовательских настроек приложения.
 *
 * Сейчас хранит:
 *   - Пол сотрудника ("male" / "female"). По умолчанию — "male".
 *   - Возраст.
 *   - Уровень подготовки.
 *
 * Данные сохраняются между запусками приложения
 * (в SharedPreferences).
 */
class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(
        "fitness_ovd_prefs",
        Context.MODE_PRIVATE
    )

    /** Сохранить пол */
    fun setGender(gender: String) {
        prefs.edit { putString(KEY_GENDER, gender) }
    }

    /** Получить пол. По умолчанию — "male" */
    fun getGender(): String {
        return prefs.getString(KEY_GENDER, "male") ?: "male"
    }

    /** Сохранить возраст */
    fun setAge(age: Int) {
        prefs.edit { putInt(KEY_AGE, age) }
    }

    /** Получить возраст. По умолчанию — 30 */
    fun getAge(): Int {
        return prefs.getInt(KEY_AGE, 30)
    }

    /** Сохранить уровень */
    fun setLevel(level: String) {
        prefs.edit { putString(KEY_LEVEL, level) }
    }

    /** Получить уровень. По умолчанию — "base" */
    fun getLevel(): String {
        return prefs.getString(KEY_LEVEL, "base") ?: "base"
    }

    /** Сохранить все данные сразу */
    fun saveAll(gender: String, age: Int, level: String) {
        prefs.edit {
            putString(KEY_GENDER, gender)
            putInt(KEY_AGE, age)
            putString(KEY_LEVEL, level)
        }
    }

    /** Очистить всё */
    fun clear() {
        prefs.edit { clear() }
    }

    companion object {
        private const val KEY_GENDER = "gender"
        private const val KEY_AGE    = "age"
        private const val KEY_LEVEL  = "level"
    }
}
