package com.example.fitapp.data.local.di

import com.example.fitapp.data.local.database.StepsDao
import com.example.fitapp.data.local.repository.SensorRepository
import com.example.fitapp.data.local.repository.SensorRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSensorRepository(
        stepsDao: StepsDao
    ): SensorRepository {
        return SensorRepositoryImpl(stepsDao)
    }
}