package com.example.fitapp.domain.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.fitapp.data.local.repository.SensorRepository
import com.example.fitapp.data.local.sensor.StepCounter
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


private const val TAG = " StepCounterWorker"
@HiltWorker
class StepCountWorker  @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val repository: SensorRepository,
    val stepCounter: StepCounter
): CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val stepSinceLastReboot = stepCounter.steps()
        if (stepSinceLastReboot == 0L) return Result.success()
        repository.storeSteps(stepSinceLastReboot)
        return Result.success()

    }
}