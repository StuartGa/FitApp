package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,
    val targetValue: Double,
    val currentValue: Double = 0.0,
    val unit: String,
    val startDate: Long = System.currentTimeMillis(),
    val endDate: Long? = null,
    val isActive: Boolean = true,
    val userEmail: String
)

enum class GoalType(val key: String) {
    DAILY_STEPS("daily_steps"),
    CALORIES("calories"),
    WORKOUTS_PER_WEEK("workouts_per_week"),
    WEIGHT("weight"),
    WATER_INTAKE("water_intake"),
    ACTIVE_MINUTES("active_minutes")
}
