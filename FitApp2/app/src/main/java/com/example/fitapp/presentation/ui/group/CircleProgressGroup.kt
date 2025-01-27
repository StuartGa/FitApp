package com.example.fitapp.presentation.ui.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.R
import com.example.fitapp.domain.model.CircleModel
import com.example.fitapp.domain.model.CircleType
import com.example.fitapp.presentation.ui.component.CircleProgress

@Composable
fun CircleProgressGroup(list: List<CircleModel> = emptyList()) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
       modifier = Modifier.fillMaxWidth()
           .padding(top = 4.dp)
    ){

        list.map {
            when(it.type) {
                CircleType.RUNNING -> {
                    CircleProgress(circleModel = it.copy(icon = R.drawable.baseline_directions_run_24, iconColor = Color.Green, circularColor = Color.Green))
                }
                CircleType.STAND -> {
                    CircleProgress(circleModel = it.copy(icon = R.drawable.baseline_arrow_upward_24, iconColor = Color.Green, circularColor = Color.Green))
                }
                CircleType.CALORIES -> {
                    CircleProgress(circleModel = it.copy(icon = R.drawable.baseline_local_fire_department_24, iconColor = Color.Green, circularColor = Color.Green))
            }

        }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CircleProgressGroupPreview() {
    CircleProgressGroup(list = listOf(CircleModel(CircleType.RUNNING,
        percentage = 0.8f, iconColor = Color.Green, icon = R.drawable.running_img, circularColor = Color.Green)))
}