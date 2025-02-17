package com.example.fitapp.domain.usecases

import com.example.fitapp.data.local.repository.SensorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStepsUseCase @Inject constructor(
    private val sensorRepository: SensorRepository
)
{
     operator fun invoke(): Flow<Result<Long>> {
        return sensorRepository.getSteps()
    }
}