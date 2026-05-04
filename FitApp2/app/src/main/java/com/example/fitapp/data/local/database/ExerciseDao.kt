package com.example.fitapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitapp.domain.entities.ExerciseEntity

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    suspend fun getAllExercises(): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE category = :category")
    suspend fun getExercisesByCategory(category: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE muscleGroup = :muscle")
    suspend fun getExercisesByMuscle(muscle: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE name LIKE '%' || :query || '%'")
    suspend fun searchExercises(query: String): List<ExerciseEntity>

    @Query("SELECT DISTINCT category FROM exercises")
    suspend fun getCategories(): List<String>

    @Query("SELECT DISTINCT muscleGroup FROM exercises")
    suspend fun getMuscleGroups(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<ExerciseEntity>)
}
