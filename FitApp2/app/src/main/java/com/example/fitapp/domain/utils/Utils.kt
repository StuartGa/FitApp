package com.example.fitapp.domain.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

object Utils {

    inline  fun getFormattedDate(): String {
        // Get the current date
        val now = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()

        // Format the date
        val dayOfWeek = now.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) // e.g., "Tuesday"
        val dayOfMonth = now.dayOfMonth // e.g., 20
        val month = now.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) // e.g., "March"
        val year = now.year // e.g., 2018

        return "$dayOfWeek, $dayOfMonth $month $year"
    }
}