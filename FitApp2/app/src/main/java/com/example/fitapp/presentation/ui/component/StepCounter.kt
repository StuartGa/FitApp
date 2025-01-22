package com.example.fitapp.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.R

@Composable
fun StepCounter(steps: Long,
                stepGoal: Long = 10000) {
    Column(Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        val progress = remember(steps) { steps.toFloat() / stepGoal }


        Image(
            painter = painterResource(id = R.drawable.im_steps),
            contentDescription = "Step counter",
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
            Text(text = steps.toString(), modifier = Modifier.padding(2.dp),
                fontWeight = Bold)
            Text(text = "steps", modifier = Modifier.padding(2.dp))
        }
        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 100f) },
            color = androidx.compose.ui.graphics.Color.Green,
            trackColor = androidx.compose.ui.graphics.Color.LightGray,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            gapSize = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .padding(horizontal = 24.dp),
        )


    }
}

@Preview(showBackground = true)
@Composable
fun StepCounterPreview() {
    StepCounter(steps = 100)
}