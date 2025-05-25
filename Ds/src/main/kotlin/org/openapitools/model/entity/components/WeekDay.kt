package org.openapitools.model.entity.components

import java.time.DayOfWeek
import java.time.LocalDate

enum class WeekDay(val shortName: String) {
    Mon("Mon"),
    Tue("Tue"),
    Wed("Wed"),
    Thu("Thu"),
    Fri("Fri"),
    Sat("Sat"),
    Sun("Sun");

    companion object {
        fun fromShortName(value: String): WeekDay =
            entries.find { it.shortName.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown day: $value")

        fun fromLocalDate(date: LocalDate): WeekDay {
            return when (date.dayOfWeek) {
                DayOfWeek.MONDAY -> Mon
                DayOfWeek.TUESDAY -> Tue
                DayOfWeek.WEDNESDAY -> Wed
                DayOfWeek.THURSDAY -> Thu
                DayOfWeek.FRIDAY -> Fri
                DayOfWeek.SATURDAY -> Sat
                DayOfWeek.SUNDAY -> Sun
            }
        }
    }

    fun next(): WeekDay = entries[(ordinal + 1) % 7]
}