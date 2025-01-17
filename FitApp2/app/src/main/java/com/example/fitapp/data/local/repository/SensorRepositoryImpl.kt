package com.example.fitapp.data.local.repository

import com.example.fitapp.data.local.database.StepsDao
import com.example.fitapp.domain.entities.StepsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SensorRepositoryImpl(private val stepsDao: StepsDao): SensorRepository{


    override suspend fun storeSteps(stepsSinceLastReboot: Long) = withContext(Dispatchers.IO) {
         val stepCount = StepsEntity(
             steps = stepsSinceLastReboot,
             createdAt = Instant.now().toString()
         )
         stepsDao.insertAll(stepCount)
     }
    override fun getSteps(): Flow<Result<Long>> = flow {
        val todayAtMidnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).toString()
        val todayDataPoints = stepsDao.loadStepsByUserToday(todayAtMidnight)

        val result = when {
            todayDataPoints.isEmpty() -> Result.failure(Exception("No data for today"))
            else -> {
                val firstDataPointOfTheDay = todayDataPoints.first()
                val latestDataPointSoFar = todayDataPoints.last()

                val todaySteps = latestDataPointSoFar.steps - firstDataPointOfTheDay.steps
                Result.success(todaySteps)
            }
        }

        emit(result)
    }.catch { e ->
        emit(Result.failure(e))
    }



}
