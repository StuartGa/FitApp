package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "body_measurements")
data class BodyMeasurementEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userEmail: String,
    val weightKg: Double,
    val bodyFatPercentage: Double = 0.0,
    val muscleMassKg: Double = 0.0,
    val chestCm: Double = 0.0,
    val waistCm: Double = 0.0,
    val hipsCm: Double = 0.0,
    val armsCm: Double = 0.0,
    val thighsCm: Double = 0.0,
    val recordedAt: Long = System.currentTimeMillis(),
    val notes: String = ""
)
