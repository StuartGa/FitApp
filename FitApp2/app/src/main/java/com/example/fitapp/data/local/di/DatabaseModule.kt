package com.example.fitapp.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.fitapp.data.local.database.AppDatabase
import com.example.fitapp.data.local.database.StepsDao
import com.example.fitapp.data.local.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "step_database",
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideStepsDao(database: AppDatabase): StepsDao {
        return database.stepsDao()
    }
}