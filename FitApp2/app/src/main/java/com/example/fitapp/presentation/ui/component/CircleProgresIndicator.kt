package com.example.fitapp.presentation.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitapp.R

@Composable
fun CircleProgresIndicator(progress: Float, // Value between 0f and 1f
                           imageRes: Int,
                           kilometers: Float,
                           goal: Float,
                           units: String = "km")

{

    var progress by remember { mutableStateOf(0.5f) } // Example progress
    val imageRes = R.drawable.im_steps
    val kilometers = 5.0f
    val goal = 10.0f


    Column() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Canvas(modifier = Modifier.size(100.dp)) {
                val circleSize = size.minDimension / 2
                val strokeWidth = 12.dp.toPx()

                drawCircle(
                    color = Color.LightGray,
                    radius = circleSize - strokeWidth / 2,
                    style = Stroke(strokeWidth)
                )

                // Progress circle
                drawArc(
                    color = Color.Blue,
                    startAngle = -90f,
                    sweepAngle = 360 * progress,
                    useCenter = false,
                    style = Stroke(strokeWidth)
                )
            }

            // Image in the center
            Image(
                painter = painterResource(id = R.drawable.im_steps),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp) // Adjust size of the image
            )

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "$kilometers $units", fontSize = 18.sp, color = Color.Black)
            Text(text = "${(progress * 100).toInt()}% of goal", fontSize = 14.sp, color = Color.Gray)
        }
        }
    }

@Preview(showBackground = true)
@Composable
fun RunningComponentPreview() {
    CircleProgresIndicator( progress = 0.5f, imageRes = R.drawable.im_steps, kilometers = 1000f, goal = 10000f)
}


