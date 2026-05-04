package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_points")
data class LocationPointEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val routeId: Long,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double = 0.0,
    val speed: Float = 0f,
    val timestamp: Long = System.currentTimeMillis()
)
