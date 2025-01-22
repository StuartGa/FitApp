package com.example.fitapp

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.fitapp.domain.worker.StepCountWorker
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
@RequiresApi(Build.VERSION_CODES.S)
internal class FitApplication : Application(), Configuration.Provider {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (sensor != null) {
            Log.d("FitApplication", "Sensor found")
        } else {
            Log.d("FitApplication", "Sensor not found")
        }

        val myWork = PeriodicWorkRequestBuilder<StepCountWorker>(
            15, TimeUnit.MINUTES
        ).build()
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "MyUniqueWorkName",
                ExistingPeriodicWorkPolicy.UPDATE, myWork
            )

        // Initialize FirebaseApp here
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this) // Ensure Firebase is initialized
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
}

