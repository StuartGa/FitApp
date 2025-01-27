package com.example.fitapp.presentation.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitapp.R


@Composable
fun CircleProgress(
    percentage: Float,
    iconColor: Color = Color.Red,
    color: Color = Color.Blue,
    strokeWidth: Dp = 12.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    imageRes: Int
)
{
    var animationPlayed by remember { mutableStateOf(false) }

    val currentPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Column() {
    Box(
        contentAlignment = Alignment.Center,

    ) {

        Canvas(modifier = Modifier.size(80.dp)) {
            drawCircle(
                color = Color.DarkGray,
                radius = size.width / 2,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

        }
        Icon(
            painter = painterResource(id = imageRes),
            contentDescription = "icon",
            tint = iconColor,
            modifier = Modifier
                .align(Alignment.Center)
                .size(40.dp)
        )
    }
        Text(text = "${(currentPercentage.value * 100).toInt()}%", fontSize = 18.sp, color = Color.Black)



    }



}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CircleProgressGroupPreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        CircleProgress(percentage = 0.8f, iconColor = Color.Green, imageRes = R.drawable.running_img)

    }
}


