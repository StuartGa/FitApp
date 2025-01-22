package com.example.fitapp.data.local.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

private const val TAG = "STEP_COUNT_LISTENER"

class StepCounter @Inject constructor(@ApplicationContext context: Context) {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)


    suspend fun steps() = suspendCancellableCoroutine { continuation ->
        Log.d(TAG, "Registering sensor listener... ")

        val listener: SensorEventListener by lazy {
            object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    if (event == null) return

                    val stepsSinceLastReboot = event.values[0].toLong()
                    Log.d(TAG, "Steps since last reboot: $stepsSinceLastReboot")

                    if (continuation.isActive) {
                        continuation.resume(stepsSinceLastReboot)
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                    Log.d(TAG, "Accuracy changed to: $accuracy")
                }
            }
        }

        val supportedAndEnabled = sensorManager.registerListener(listener,
            sensor, SensorManager.SENSOR_DELAY_UI)
        Log.d(TAG, "Sensor listener registered: $supportedAndEnabled")
    }
}