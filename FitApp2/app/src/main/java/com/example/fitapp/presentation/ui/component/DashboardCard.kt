package com.example.fitapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.R

@Composable
fun DashboardCard(icon: Int, title: String, count: String, units: String = "min") {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = units, fontWeight = FontWeight.Bold)
    }
}



@Preview(showBackground = true)
@Composable
fun DashboardCardPreview() {
    DashboardCard(icon = R.drawable.stan_img, title = "Running", count = "10 min")
}