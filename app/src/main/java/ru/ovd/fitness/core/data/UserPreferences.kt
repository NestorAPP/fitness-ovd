package ru.ovd.fitness.core.data

import android.content.Context
import androidx.core.content.edit

/**
 * Хранилище пользовательских настроек приложения.
 *
 * Сейчас хранит:
 *   - Пол сотрудника ("male" / "female").
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

    /** Получить пол. null — если сотрудник ещё не вводил */
    fun getGender(): String? {
        return prefs.getString(KEY_GENDER, null)
    }

    /** Сохранить возраст */
    fun setAge(age: Int) {
        prefs.edit { putInt(KEY_AGE, age) }
    }

    /** Получить возраст. null — если ещё не вводил */
    fun getAge(): Int? {
        val age = prefs.getInt(KEY_AGE, -1)
        return if (age == -1) null else age
    }

    /** Сохранить уровень */
    fun setLevel(level: String) {
        prefs.edit { putString(KEY_LEVEL, level) }
    }

    /** Получить уровень. null — если ещё не вводил */
    fun getLevel(): String? {
        return prefs.getString(KEY_LEVEL, null)
    }

    /** Сохранить все данные сразу */
    fun saveAll(gender: String, age: Int, level: String) {
        prefs.edit {
            putString(KEY_GENDER, gender)
            putInt(KEY_AGE, age)
            putString(KEY_LEVEL, level)
        }
    }

    /** Очистить всё (например, при выходе) */
    fun clear() {
        prefs.edit { clear() }
    }

    companion object {
        private const val KEY_GENDER = "gender"
        private const val KEY_AGE    = "age"
        private const val KEY_LEVEL  = "level"
    }
}
