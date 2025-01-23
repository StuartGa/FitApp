package com.example.fitapp.presentation.ui.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DashboardHorizontalDivider() {
    HorizontalDivider(color = Color.DarkGray, thickness = 2.dp,modifier = Modifier.fillMaxWidth()
        .padding(top = 16.dp)
        .height(20.dp))
}
@Composable
fun DashboardVerticalDivider() {
   VerticalDivider(color = Color.LightGray, thickness = 2.dp,
       modifier = Modifier.wrapContentHeight()
   )
}



@Preview(showBackground = true)
@Composable
fun DashboardDividerPreview() {
    DashboardHorizontalDivider()
}
@Preview(showBackground = true)
@Composable
fun DashboardVerticalDividerPreview() {
    DashboardVerticalDivider()
}