package com.example.fitapp.presentation.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitapp.presentation.ui.component.BottomNavItem
import com.example.fitapp.presentation.ui.component.FitBottomNav
import com.example.fitapp.presentation.ui.screens.DashboardScreen
import com.example.fitapp.presentation.ui.screens.exercise.ExerciseLibraryScreen
import com.example.fitapp.presentation.ui.screens.profile.ProfileScreen
import com.example.fitapp.presentation.ui.viewModel.ExerciseLibraryViewModel
import com.example.fitapp.presentation.ui.viewModel.ProfileViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(BottomNavItem.HOME) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            FitBottomNav(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                BottomNavItem.HOME -> DashboardScreen()
                BottomNavItem.WORKOUTS -> {
                    val vm: ExerciseLibraryViewModel = hiltViewModel()
                    ExerciseLibraryScreen(viewModel = vm)
                }
                BottomNavItem.TRACKING -> PlaceholderScreen("Activity Tracking")
                BottomNavItem.PROGRESS -> PlaceholderScreen("Progress")
                BottomNavItem.PROFILE -> {
                    val profileViewModel: ProfileViewModel = hiltViewModel()
                    ProfileScreen(viewModel = profileViewModel)
                }
            }
        }
    }
}

@Composable
private fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
