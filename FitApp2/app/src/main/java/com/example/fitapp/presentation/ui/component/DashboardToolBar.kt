package com.example.fitapp.presentation.ui.component

import MainActivityEvent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardToolBar(callback: (event : MainActivityEvent) -> Unit){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Black,
            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = Color.White,
        ),
        navigationIcon = {
           DropdownMenuWithDetails(callback)
        },
        actions = {
            TextButton(onClick = {  }) {
                Text(text = "Edit",
                    color = Color.White)
            }
        },
        title = {
            Text(text = "Activity")
        },
        modifier = Modifier.fillMaxWidth()
            .padding(0.dp)
    )
}


@Preview
@Composable
fun PreviewDashbordToolBar(){
    DashboardToolBar({})
}