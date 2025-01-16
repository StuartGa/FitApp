package com.example.fitapp.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps_table")
data class StepsEntity (
    @ColumnInfo(name = "steps") val steps: Long,
    @PrimaryKey @ColumnInfo(name = "created_at") val createdAt: String,
)