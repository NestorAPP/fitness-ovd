package ru.ovd.fitness.core.data

import androidx.room.TypeConverter

/**
 * Конвертеры типов для Room.
 *
 * Room не умеет сохранять Number «из коробки», но умеет Double.
 * Эти конвертеры учат Room превращать Number ↔ Double.
 */
class Converters {

    @TypeConverter
    fun fromNumber(value: Number?): Double? {
        return value?.toDouble()
    }

    @TypeConverter
    fun toNumber(value: Double?): Number? {
        return value
    }
}
