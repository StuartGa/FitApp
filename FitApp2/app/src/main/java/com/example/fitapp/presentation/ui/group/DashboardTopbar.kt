package com.example.fitapp.presentation.ui.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fitapp.presentation.ui.component.DashboardToolBar
import com.example.fitapp.presentation.ui.component.DateText
import java.time.Instant

@Composable
fun DashboardTopbar(){
    Column(Modifier.fillMaxWidth()) {
        DashboardToolBar()
        DashboardTapGroup( listOf("Today", "Week", "Month", "Year"))
        DateText(Instant.now().toString(), Modifier)
    }
}