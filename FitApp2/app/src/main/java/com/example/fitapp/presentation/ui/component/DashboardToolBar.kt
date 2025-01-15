package com.example.fitapp.presentation.ui.component

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
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardToolBar(){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            TextButton(onClick = {  }) {
                Text(text = "Edit")
            }
        },
        title = {
            Text(text = "Activity")
        }
    )
}


@Preview
@Composable
fun PreviewDashbordToolBar(){
    DashboardToolBar()
}