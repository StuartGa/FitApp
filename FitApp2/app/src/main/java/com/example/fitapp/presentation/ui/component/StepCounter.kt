package com.example.fitapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.R
import com.example.fitapp.presentation.ui.theme.FitAppTheme

@Composable
fun StepCounter(
    steps: Long,
    stepGoal: Long = 1000,
) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center


    ) {
        val progress = remember(steps, stepGoal) { steps.toFloat() / stepGoal }


        Icon(
            painter = painterResource(id = R.drawable.im_steps),
            contentDescription = "Step counter",
            modifier = Modifier.fillMaxWidth(),
            tint = Color.Green
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = steps.toString(),
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.displayLarge,

            )
            Text(text = "steps", modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.titleMedium)
        }
        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, stepGoal.toFloat()) },
            color = androidx.compose.ui.graphics.Color.Green,
            trackColor = androidx.compose.ui.graphics.Color.LightGray,
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
            gapSize = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .padding( start = 16.dp, end = 16.dp, top = 2.dp)
        )


    }
}

@Preview(showBackground = true, backgroundColor = 0x00000000)
@Composable
fun StepCounterPreview() {
    FitAppTheme(darkTheme = true) {
        StepCounter(steps = 100)
    }
}