package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val email: String,
    val displayName: String,
    val age: Int = 0,
    val weightKg: Double = 0.0,
    val heightCm: Double = 0.0,
    val gender: String = "",
    val avatarUrl: String = "",
    val updatedAt: Long = System.currentTimeMillis()
)
