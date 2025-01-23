package com.example.fitapp.domain.model

import android.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class CircleModel {
    data class Running(val kilometers: Float, val percentage: Float, val iconColor: Color, val color: Color, val strokeWidth: Dp? = 12.dp, val icon: Int) : CircleModel()
    data class Calories(val calories: Long, val percentage: Float, val iconColor: Color, val color: Color, val strokeWidth: Dp? = 12.dp, val icon: Int) : CircleModel()
    data class Stand(val stands: Long, val percentage: Float, val iconColor: Color, val color: Color, val strokeWidth: Dp? = 12.dp, val icon: Int) : CircleModel()
}