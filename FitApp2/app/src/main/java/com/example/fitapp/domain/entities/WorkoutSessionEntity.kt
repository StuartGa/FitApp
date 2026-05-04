package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_sessions")
data class WorkoutSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workoutId: Long,
    val startedAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null,
    val totalCalories: Double = 0.0,
    val durationSeconds: Long = 0,
    val userEmail: String
)
