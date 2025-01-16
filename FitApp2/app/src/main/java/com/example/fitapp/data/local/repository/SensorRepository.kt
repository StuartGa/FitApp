package com.example.fitapp.data.local.repository


import kotlinx.coroutines.flow.Flow

interface SensorRepository {

    suspend fun storeSteps(stepsSinceLastReboot: Long)
     fun getSteps(): Flow<Result<Long>>
}