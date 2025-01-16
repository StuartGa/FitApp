package com.example.fitapp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.Greeting
import com.example.fitapp.presentation.ui.component.DashboardTapGroup
import com.example.fitapp.presentation.ui.component.DashboardToolBar
import com.example.fitapp.presentation.ui.component.DateText
import com.example.fitapp.presentation.ui.theme.FitAppTheme

@Composable
fun DashboardScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                DashboardToolBar()
                DashboardTapGroup( listOf("Today", "Week", "Month", "Year"))
                DateText("16 DE JUNIO DE 2022", Modifier)
            }

        }) { innerPadding ->

        Column(Modifier.fillMaxSize()){
            Greeting(
                name = "FitApp",
                modifier = Modifier.padding(innerPadding)
            )
        }

    }


}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    FitAppTheme {
        DashboardScreen()
    }
}