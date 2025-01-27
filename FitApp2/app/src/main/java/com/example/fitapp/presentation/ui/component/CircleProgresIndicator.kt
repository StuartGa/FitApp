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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitapp.R
import com.example.fitapp.domain.model.CircleModel


@Composable
fun CircleProgress(
    circleModel: CircleModel
)
{
    var animationPlayed by remember { mutableStateOf(false) }

    val currentPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) 0.8f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 0
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
                style = Stroke(12.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = circleModel.,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(12.dp.toPx(), cap = StrokeCap.Round)
            )

        }
        Icon(
            painter = painterResource(id = circleModel.icon),
            contentDescription = "icon",
            tint = circleModel.iconColor,
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
        CircleProgress(
            circleModel = CircleModel.Running(kilometers = 10f,
            percentage = 0.8f, iconColor = Color.Green, icon = R.drawable.running_img, circularColor = Color.Green))

    }
}


