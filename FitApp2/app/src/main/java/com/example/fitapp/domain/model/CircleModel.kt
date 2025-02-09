package com.example.fitapp.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CircleModel(val type: CircleType, val percentage: Float, val iconColor: Color, val circularColor: Color, val icon: Int)


enum class CircleType {
    CALORIES, STAND, RUNNING
}