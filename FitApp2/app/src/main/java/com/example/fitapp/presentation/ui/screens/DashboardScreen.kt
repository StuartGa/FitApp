package com.example.fitapp.presentation.ui.screens

import MainActivityState
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.Greeting
import com.example.fitapp.presentation.ui.group.DashboardTapGroup
import com.example.fitapp.presentation.ui.component.DashboardToolBar
import com.example.fitapp.presentation.ui.component.DateText
import com.example.fitapp.presentation.ui.group.DashboardTopbar
import com.example.fitapp.presentation.ui.theme.FitAppTheme
import java.time.Instant

@Composable
fun DashboardScreen(steps: Long) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
                DashboardTopbar()

        }) { innerPadding ->

        TodayScreen(innerPadding, steps)

    }


}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    FitAppTheme {
        DashboardScreen(100L)
    }
}