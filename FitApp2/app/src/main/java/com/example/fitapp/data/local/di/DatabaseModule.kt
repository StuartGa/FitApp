package com.example.fitapp.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.fitapp.data.local.database.AppDatabase
import com.example.fitapp.data.local.database.BodyMeasurementDao
import com.example.fitapp.data.local.database.GoalDao
import com.example.fitapp.data.local.database.StepsDao
import com.example.fitapp.data.local.database.UserDao
import com.example.fitapp.data.local.database.UserProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "step_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Singleton @Provides
    fun provideStepsDao(database: AppDatabase): StepsDao = database.stepsDao()

    @Singleton @Provides
    fun provideUserProfileDao(database: AppDatabase): UserProfileDao = database.userProfileDao()

    @Singleton @Provides
    fun provideGoalDao(database: AppDatabase): GoalDao = database.goalDao()

    @Singleton @Provides
    fun provideBodyMeasurementDao(database: AppDatabase): BodyMeasurementDao = database.bodyMeasurementDao()
}
