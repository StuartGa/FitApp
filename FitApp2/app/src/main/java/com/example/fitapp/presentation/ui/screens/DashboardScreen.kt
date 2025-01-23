package com.example.fitapp.presentation.ui.screens

import MainActivityEffect
import MainActivityEvent
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.fitapp.LoginActivity
import com.example.fitapp.presentation.ui.group.DashboardTopbar
import com.example.fitapp.presentation.ui.theme.FitAppTheme

@Composable
fun DashboardScreen(steps: Long, onClickMenu: (event : MainActivityEvent) -> Unit) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
                DashboardTopbar(onClickMenu)

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