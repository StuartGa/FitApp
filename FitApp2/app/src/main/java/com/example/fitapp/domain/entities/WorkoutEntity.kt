package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String = "",
    val category: String = "",
    val estimatedDurationMinutes: Int = 0,
    val isPrebuilt: Boolean = false,
    val createdByUserEmail: String = ""
)
