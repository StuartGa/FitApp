package com.example.wearos.data.remote.health.manager

import android.content.Context
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import android.util.Log
import androidx.health.services.client.HealthServices
import androidx.health.services.client.MeasureCallback
import androidx.health.services.client.data.Availability
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataTypeAvailability
import androidx.health.services.client.data.DeltaDataType
import androidx.health.services.client.data.SampleDataPoint
import androidx.health.services.client.getCapabilities
import androidx.health.services.client.unregisterMeasureCallback
import com.google.android.gms.fitness.data.DataType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "WATCHMAIN"

class HealthServicesManager @Inject constructor (@ApplicationContext private val context: Context) {
    private val measureClient = HealthServices.getClient(context).measureClient

    suspend fun hasHeartRateCapability() = runCatching {
        val capabilities = measureClient.getCapabilities()
        (androidx.health.services.client.data.DataType.HEART_RATE_BPM in capabilities.supportedDataTypesMeasure)
    }.getOrDefault(false)

    /**
     * Returns a cold flow. When activated, the flow will register a callback for heart rate data
     * and start to emit messages. When the consuming coroutine is canceled, the measure callback
     * is unregistered.
     *
     * [callbackFlow] creates a  bridge between a callback-based API and Kotlin flows.
     */
    @ExperimentalCoroutinesApi
    fun heartRateMeasureFlow(): Flow<MeasureMessage> = callbackFlow {
        val callback = object : MeasureCallback {
            override fun onAvailabilityChanged(dataType: DeltaDataType<*, *>, availability: Availability) {
                // Only send back DataTypeAvailability (not LocationAvailability)
                if (availability is DataTypeAvailability) {
                    trySend(MeasureMessage.MeasureAvailability(availability))
                }
            }

            override fun onDataReceived(data: DataPointContainer) {
                val heartRateBpm = data.getData(androidx.health.services.client.data.DataType.HEART_RATE_BPM)
                Log.d(TAG, "💓 Received heart rate: ${heartRateBpm.first().value}")
                trySend(MeasureMessage.MeasureData(heartRateBpm))
            }
        }

        measureClient.registerMeasureCallback(androidx.health.services.client.data.DataType.HEART_RATE_BPM , callback)

        awaitClose {
            Log.d(TAG, "👋 Unregistering for data")
            runBlocking {
                measureClient.unregisterMeasureCallback(androidx.health.services.client.data.DataType.HEART_RATE_BPM, callback)
            }
        }
    }
}

sealed class MeasureMessage {
    class MeasureAvailability(val availability: DataTypeAvailability) : MeasureMessage()
    class MeasureData(val data: List<SampleDataPoint<Double>>) : MeasureMessage()
}