package ru.ovd.fitness.core.data

import ru.ovd.fitness.core.data.entity.ExerciseScore

/**
 * Баллы за упражнения для мужчин (приказ МВД № 44, п. 343.1).
 *
 * ВАЖНО: файл вставляется по частям. Это — ЧАСТЬ 1 из 5.
 * В конце файла должна быть запятая — продолжение добавится следующим шагом.
 *
 * exerciseId соответствует orderNumber в ExercisesData:
 *   1 = Подтягивание на перекладине
 *   2 = Жим гири 24 кг
 *   3 = Сгибание/разгибание рук в упоре лёжа
 *   4 = Челночный бег 10×10
 *   5 = Челночный бег 4×20
 *   6 = Бег 3 км
 *   7 = Бег 5 км
 *   8 = Бег на лыжах 5 км
 *   9 = Плавание 100 м
 */
val exerciseScoresMenData: List<ExerciseScore> = listOf(

    // ═══════════════════════════════════════════
    //   УПРАЖНЕНИЕ 1: Подтягивание на перекладине
    // ═══════════════════════════════════════════
    ExerciseScore(exerciseId = 1, gender = "male", points = 100, resultDisplay = "более 22", resultMinSec = 23, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 99, resultDisplay = "22", resultMinSec = 22, resultMaxSec = 22, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 98, resultDisplay = "21", resultMinSec = 21, resultMaxSec = 21, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 97, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 96, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 95, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 94, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 93, resultDisplay = "20", resultMinSec = 20, resultMaxSec = 20, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 92, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 91, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 90, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 89, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 88, resultDisplay = "19", resultMinSec = 19, resultMaxSec = 19, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 87, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 86, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 85, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 84, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 83, resultDisplay = "18", resultMinSec = 18, resultMaxSec = 18, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 82, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 81, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 80, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 79, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 78, resultDisplay = "17", resultMinSec = 17, resultMaxSec = 17, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 77, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 76, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 75, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 74, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 73, resultDisplay = "16", resultMinSec = 16, resultMaxSec = 16, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 72, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 71, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 70, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 69, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 68, resultDisplay = "15", resultMinSec = 15, resultMaxSec = 15, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 67, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 66, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 65, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 64, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 63, resultDisplay = "14", resultMinSec = 14, resultMaxSec = 14, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 62, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 61, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 60, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 59, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 58, resultDisplay = "13", resultMinSec = 13, resultMaxSec = 13, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 57, resultDisplay = "12", resultMinSec = 12, resultMaxSec = 12, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 56, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 55, resultDisplay = "11", resultMinSec = 11, resultMaxSec = 11, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 54, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 53, resultDisplay = "10", resultMinSec = 10, resultMaxSec = 10, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 52, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 51, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 50, resultDisplay = "9", resultMinSec = 9, resultMaxSec = 9, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 49, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 48, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 47, resultDisplay = "8", resultMinSec = 8, resultMaxSec = 8, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 46, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 45, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 44, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 43, resultDisplay = "7", resultMinSec = 7, resultMaxSec = 7, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 42, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 41, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 40, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 39, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 38, resultDisplay = "6", resultMinSec = 6, resultMaxSec = 6, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 37, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 36, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 35, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 34, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 33, resultDisplay = "5", resultMinSec = 5, resultMaxSec = 5, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 32, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 31, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 30, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 29, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 28, resultDisplay = "4", resultMinSec = 4, resultMaxSec = 4, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 27, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 26, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 25, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 24, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 23, resultDisplay = "3", resultMinSec = 3, resultMaxSec = 3, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 22, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 21, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 20, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 19, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 18, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 17, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 16, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 15, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 14, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 13, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 12, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 11, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 10, resultDisplay = "2", resultMinSec = 2, resultMaxSec = 2, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 9, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 8, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 7, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 6, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 5, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 4, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 3, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 2, resultDisplay = "—", resultMinSec = null, resultMaxSec = null, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 1, resultDisplay = "1", resultMinSec = 1, resultMaxSec = 1, resultUnit = "раз"),
    ExerciseScore(exerciseId = 1, gender = "male", points = 0, resultDisplay = "0", resultMinSec = 0, resultMaxSec = 0, resultUnit = "раз"),
