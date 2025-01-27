package com.example.fitapp.presentation.ui.screens

import MainActivityEvent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitapp.presentation.ui.group.DashboardTopBar
import com.example.fitapp.presentation.ui.theme.FitAppTheme

@Composable
fun DashboardScreen(steps: Long, onClickMenu: (event : MainActivityEvent) -> Unit) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
                DashboardTopBar(onClickMenu)

        }) { innerPadding ->

        TodayScreen(innerPadding, steps)

    }


}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    FitAppTheme {
        DashboardScreen(100L,onClickMenu = {})
    }
}