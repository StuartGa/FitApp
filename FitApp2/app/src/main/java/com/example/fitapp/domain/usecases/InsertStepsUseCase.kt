package com.example.fitapp.domain.usecases

import SensorRepository
import javax.inject.Inject

class InsertStepsUseCase @Inject constructor(
    private val sensorRepository: SensorRepository
)
{
    suspend operator fun invoke(steps: Long) {
        sensorRepository.storeSteps(steps)
    }
}