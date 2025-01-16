package com.example.fitapp.domain.model

data class ScreenModel (
    val user: UserModel,
    val steps: Long,
    val calories: Long,
    val distance: Long,
    val stepsGoal: Long,
    val caloriesGoal: Long,
    val distanceGoal: Long,
)