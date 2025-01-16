package com.example.fitapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitapp.domain.entities.StepsEntity
import com.example.fitapp.domain.entities.UserEntity
import com.example.fitapp.domain.view.StepsView
import com.example.fitapp.domain.view.UserView

@Database(
    entities = [UserEntity::class, StepsEntity::class],
    views = [UserView::class, StepsView::class],
    version = 2,

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stepsDao(): StepsDao
}
