package com.example.fitapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.presentation.ui.component.DashboardCard
import com.example.fitapp.presentation.ui.group.CircleProgressGroup
import com.example.fitapp.presentation.ui.component.DashboardHorizontalDivider
import com.example.fitapp.presentation.ui.component.StepCounter
import com.example.fitapp.presentation.ui.group.DashboardCardGroup
import com.example.fitapp.presentation.ui.theme.FitAppTheme

@Composable
fun TodayScreen(iner: PaddingValues = PaddingValues(0.dp), steps: Long ) {
    Column(
        Modifier
            .padding(top = 4.dp)
    ) {
        StepCounter(steps = steps)
        DashboardHorizontalDivider()
        CircleProgressGroup(progress = 0.5f)
        DashboardHorizontalDivider()
        DashboardCardGroup()
        DashboardHorizontalDivider()
        DashboardCard(
            icon = com.example.fitapp.R.drawable.running_img,
            title = "Running",
            count = "10 min"
        )
    }
}




@Preview(showBackground = true)
@Composable
fun TodayPreview() {
    FitAppTheme {
        TodayScreen(steps = 100L)
    }
}