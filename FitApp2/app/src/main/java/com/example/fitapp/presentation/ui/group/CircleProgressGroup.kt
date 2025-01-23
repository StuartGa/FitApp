package com.example.fitapp.presentation.ui.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitapp.R
import com.example.fitapp.presentation.ui.component.CircleProgress

@Composable
fun CircleProgressGroup(progress: Float) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
       modifier = Modifier.fillMaxWidth()
    ){
        CircleProgress(percentage = 0.8f, imageRes = R.drawable.baseline_directions_run_24, color = Color.Red, iconColor = Color.Red)
        CircleProgress(percentage = 0.8f, imageRes = R.drawable.baseline_local_fire_department_24, color = Color.Green, iconColor = Color.Green)
        CircleProgress(percentage = 0.8f, imageRes = R.drawable.baseline_arrow_upward_24, color = Color.Blue, iconColor = Color.Blue)

    }
}

@Preview(showBackground = true)
@Composable
fun CircleProgressGroupPreview() {
    CircleProgressGroup(progress = 0.5f)
}