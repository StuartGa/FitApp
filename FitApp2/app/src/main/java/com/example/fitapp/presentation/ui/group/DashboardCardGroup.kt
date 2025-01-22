package com.example.fitapp.presentation.ui.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitapp.R
import com.example.fitapp.presentation.ui.component.DashboardCard
import com.example.fitapp.presentation.ui.component.DashboardVerticalDivider

@Composable
fun DashboardCardGroup() {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DashboardCard(icon = R.drawable.running_img, title = "Running", count = "10 min")
        DashboardCard(icon = R.drawable.stan_img, title = "Standing", count = "5 min")
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardCardGroupPreview() {
    DashboardCardGroup()
}