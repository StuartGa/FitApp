package com.example.fitapp.domain.view

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView("SELECT steps, created_at FROM steps_table")
data class StepsView(
    @ColumnInfo(name = "steps") val steps: Long,
    @ColumnInfo(name = "created_at") val createdAt: String
)