package com.example.fitapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitapp.domain.entities.BodyMeasurementEntity

@Dao
interface BodyMeasurementDao {
    @Query("SELECT * FROM body_measurements WHERE userEmail = :email ORDER BY recordedAt DESC")
    suspend fun getMeasurements(email: String): List<BodyMeasurementEntity>

    @Query("SELECT * FROM body_measurements WHERE userEmail = :email ORDER BY recordedAt DESC LIMIT 1")
    suspend fun getLatestMeasurement(email: String): BodyMeasurementEntity?

    @Insert
    suspend fun insert(measurement: BodyMeasurementEntity): Long
}
