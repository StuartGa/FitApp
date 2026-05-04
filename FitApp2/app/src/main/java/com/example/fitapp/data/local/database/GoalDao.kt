package com.example.fitapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fitapp.domain.entities.GoalEntity

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals WHERE userEmail = :email AND isActive = 1")
    suspend fun getActiveGoals(email: String): List<GoalEntity>

    @Query("SELECT * FROM goals WHERE userEmail = :email AND type = :type AND isActive = 1 LIMIT 1")
    suspend fun getGoalByType(email: String, type: String): GoalEntity?

    @Insert
    suspend fun insert(goal: GoalEntity): Long

    @Update
    suspend fun update(goal: GoalEntity)

    @Query("UPDATE goals SET isActive = 0 WHERE id = :goalId")
    suspend fun deactivate(goalId: Long)
}
