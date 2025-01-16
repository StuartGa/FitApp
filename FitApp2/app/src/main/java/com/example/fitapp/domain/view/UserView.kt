package com.example.fitapp.domain.view

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView("SELECT name FROM user_table")
data class UserView(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String
)