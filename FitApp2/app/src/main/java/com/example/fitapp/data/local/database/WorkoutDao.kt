package com.example.fitapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.fitapp.domain.entities.ExerciseSetEntity
import com.example.fitapp.domain.entities.WorkoutEntity
import com.example.fitapp.domain.entities.WorkoutExerciseCrossRef
import com.example.fitapp.domain.entities.WorkoutSessionEntity

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insertWorkout(workout: WorkoutEntity): Long

    @Insert
    suspend fun insertCrossRef(crossRef: WorkoutExerciseCrossRef)

    @Query("SELECT * FROM workouts")
    suspend fun getAllWorkouts(): List<WorkoutEntity>

    @Query("SELECT * FROM workout_exercise_cross_ref WHERE workoutId = :workoutId ORDER BY orderIndex ASC")
    suspend fun getExercisesForWorkout(workoutId: Long): List<WorkoutExerciseCrossRef>

    @Insert
    suspend fun startSession(session: WorkoutSessionEntity): Long

    @Update
    suspend fun updateSession(session: WorkoutSessionEntity)

    @Insert
    suspend fun logSet(set: ExerciseSetEntity)

    @Query("UPDATE exercise_sets SET isCompleted = 1, reps = :reps, weightKg = :weight WHERE id = :setId")
    suspend fun completeSet(setId: Long, reps: Int, weight: Double)

    @Query("SELECT * FROM workout_sessions WHERE userEmail = :email ORDER BY startedAt DESC LIMIT 30")
    suspend fun getRecentSessions(email: String): List<WorkoutSessionEntity>
}
