package com.example.fitapp.presentation.ui.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitapp.R
import com.example.fitapp.presentation.ui.component.CircleProgresIndicator

@Composable
fun CircleProgressGroup(progress: Float) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        CircleProgresIndicator( progress = 0.5f, imageRes = R.drawable.running_img, kilometers = 1000f, goal = 10000f)
        CircleProgresIndicator( progress = 0.5f, imageRes = R.drawable.stan_img, kilometers = 1000f, goal = 10000f)
        CircleProgresIndicator(progress = 0.5f, imageRes = R.drawable.im_steps, kilometers = 1000f, goal = 10000f)

    }
}

@Preview(showBackground = true)
@Composable
fun CircleProgressGroupPreview() {
    CircleProgressGroup(progress = 0.5f)
}