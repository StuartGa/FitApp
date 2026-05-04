package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes")
data class RouteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workoutSessionId: Long = 0,
    val distanceMeters: Double = 0.0,
    val durationSeconds: Long = 0,
    val averagePaceSecondsPerKm: Double = 0.0,
    val averageSpeedKmh: Double = 0.0,
    val maxSpeedKmh: Double = 0.0,
    val caloriesBurned: Double = 0.0,
    val elevationGainMeters: Double = 0.0,
    val activityType: String = "running",
    val startedAt: Long = System.currentTimeMillis()
)
