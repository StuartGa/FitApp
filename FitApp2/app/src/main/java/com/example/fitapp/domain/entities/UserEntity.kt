package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    val name: String,
   @PrimaryKey val email: String,
)
