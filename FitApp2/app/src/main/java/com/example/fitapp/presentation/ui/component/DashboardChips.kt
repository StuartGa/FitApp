package com.example.fitapp.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OutlineFilterChip(period: String) {
    var selected by remember { mutableStateOf(false) }
            TextButton(
                onClick = { selected = !selected },
                modifier = Modifier
                    .drawBehind {
                        val underlineColor = if (selected) Color.Yellow else Color.Transparent
                        val lineThickness = 2.dp.toPx()
                        drawLine(
                            color = underlineColor,
                            start = androidx.compose.ui.geometry.Offset(0f, size.height),
                            end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                            strokeWidth = lineThickness
                        )
                    },
                        content ={
                    Text(
                        modifier = Modifier.padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        color = if(selected) Color.Yellow else Color.White,
                        text = period,
                    )
                },
            )
    }






@Preview
@Composable
fun DashboardChipsPreview(){
    OutlineFilterChip("Today")
}