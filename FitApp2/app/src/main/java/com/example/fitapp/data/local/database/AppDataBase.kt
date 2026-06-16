package com.example.fitapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitapp.domain.entities.StepsEntity
import com.example.fitapp.domain.entities.UserEntity

@Database(entities = [UserEntity::class, StepsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stepsDao(): StepsDao
}
