package com.example.fitapp.presentation.ui.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitapp.presentation.ui.component.OutlineFilterChip

@Composable
fun DashboardTapGroup(list:List<String>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
        .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ){
        list.forEach {
            OutlineFilterChip(it)
        }
    }
}

@Preview
@Composable
fun DashboardTapGroupPreview(){
    DashboardTapGroup(list = listOf("Today","Week","Month","Year"))
}