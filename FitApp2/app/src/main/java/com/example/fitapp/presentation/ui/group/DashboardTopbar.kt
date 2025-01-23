package com.example.fitapp.presentation.ui.group

import MainActivityEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.domain.utils.Utils
import com.example.fitapp.presentation.ui.component.DashboardToolBar
import com.example.fitapp.presentation.ui.component.DateText
import com.example.fitapp.presentation.ui.theme.FitAppTheme

@Composable
fun DashboardTopbar(callback: (event: MainActivityEvent) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DashboardToolBar(callback)
        DashboardTapGroup(listOf("Today", "Week", "Month", "Year"))
        DateText(Utils.getFormattedDate())
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardTopbarPreview(){
    FitAppTheme {
        DashboardTopbar({})
    }
}