package com.example.fitapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitapp.domain.entities.StepsEntity

@Dao
interface StepsDao {
    @Query("SELECT steps, created_at FROM steps_table WHERE created_at >= date(:startDateTime) " +
            "AND created_at <= date(:startDateTime, '+1 day')")
    suspend fun loadStepsByUserToday(startDateTime: String): Array<StepsEntity>

    @Insert
    suspend fun insert(steps: StepsEntity)

    @Insert
    suspend fun insertAll(vararg steps: StepsEntity)
}
