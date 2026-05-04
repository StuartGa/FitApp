package com.example.fitapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitapp.domain.entities.BodyMeasurementEntity
import com.example.fitapp.domain.entities.ExerciseEntity
import com.example.fitapp.domain.entities.ExerciseSetEntity
import com.example.fitapp.domain.entities.GoalEntity
import com.example.fitapp.domain.entities.StepsEntity
import com.example.fitapp.domain.entities.UserEntity
import com.example.fitapp.domain.entities.UserProfileEntity
import com.example.fitapp.domain.entities.WorkoutEntity
import com.example.fitapp.domain.entities.WorkoutExerciseCrossRef
import com.example.fitapp.domain.entities.WorkoutSessionEntity
import com.example.fitapp.domain.view.StepsView
import com.example.fitapp.domain.view.UserView

@Database(
    entities = [
        UserEntity::class,
        StepsEntity::class,
        UserProfileEntity::class,
        GoalEntity::class,
        BodyMeasurementEntity::class,
        ExerciseEntity::class,
        WorkoutEntity::class,
        WorkoutExerciseCrossRef::class,
        WorkoutSessionEntity::class,
        ExerciseSetEntity::class
    ],
    views = [UserView::class, StepsView::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stepsDao(): StepsDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun goalDao(): GoalDao
    abstract fun bodyMeasurementDao(): BodyMeasurementDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
}
