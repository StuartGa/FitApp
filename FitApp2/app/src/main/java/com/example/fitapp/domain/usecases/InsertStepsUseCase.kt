package com.example.fitapp.domain.usecases

import com.example.fitapp.data.local.repository.SensorRepository
import javax.inject.Inject

class InsertStepsUseCase @Inject constructor(
    private val sensorRepository: SensorRepository
)
{
    suspend operator fun invoke(steps: Long) {
        sensorRepository.storeSteps(steps)
    }
}