package com.example.fitapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userEmail: String,
    val achievementKey: String,
    val title: String,
    val description: String,
    val unlockedAt: Long = System.currentTimeMillis(),
    val iconName: String = "emoji_events"
)

object AchievementData {
    val achievements = listOf(
        AchievementTemplate("first_workout", "First Steps", "Complete your first workout", "emoji_events"),
        AchievementTemplate("workout_5", "Getting Stronger", "Complete 5 workouts", "fitness_center"),
        AchievementTemplate("workout_10", "Dedicated", "Complete 10 workouts", "local_fire_department"),
        AchievementTemplate("workout_25", "Warrior", "Complete 25 workouts", "military_tech"),
        AchievementTemplate("workout_50", "Legend", "Complete 50 workouts", "star"),
        AchievementTemplate("streak_3", "3-Day Streak", "Workout 3 days in a row", "trending_up"),
        AchievementTemplate("streak_7", "Weekly Warrior", "Workout 7 days in a row", "whatshot"),
        AchievementTemplate("streak_30", "Monthly Beast", "Workout 30 days in a row", "diamond"),
        AchievementTemplate("steps_10k", "10K Club", "Reach 10,000 steps in a day", "directions_walk"),
        AchievementTemplate("calories_500", "Calorie Burner", "Burn 500 calories in a workout", "fireplace"),
        AchievementTemplate("weight_goal", "Goal Crusher", "Reach your target weight", "check_circle"),
        AchievementTemplate("pr_set", "Personal Best", "Set a new personal record", "new_releases")
    )
}

data class AchievementTemplate(
    val key: String,
    val title: String,
    val description: String,
    val iconName: String
)
