package ru.ovd.fitness.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Минимальное количество баллов для присвоения квалификационного звания.
 *
 * Источник: приказ МВД № 44, пункт 365.3.2.
 *
 * Звания (от младшего к старшему):
 *   - "Специалист третьего класса"
 *   - "Специалист второго класса"
 *   - "Специалист первого класса"
 *   - "Мастер"
 *
 * Уровни: "base" (базовый), "enhanced" (усиленный), "special" (специальный).
 * Пол: "male" / "female".
 *
 * Если сотрудник 8-й группы (женщины) не может получить звание —
 * запись для него не создаётся (прочерк в приказе).
 */
@Entity(tableName = "qualification_scores")
data class QualificationScore(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** Название звания: "Специалист третьего класса" и т.д. */
    val qualificationName: String,

    /** Порядок звания: 1 = Специалист 3 класса, ..., 4 = Мастер */
    val qualificationOrder: Int,

    /** Пол: "male" / "female" */
    val gender: String,

    /** Уровень: "base" / "enhanced" / "special" */
    val level: String,

    /** Возрастная группа: 1..8 (муж), 1..7 (жен) */
    val groupNumber: Int,

    /** Минимальный балл для получения звания */
    val minPoints: Int
)
