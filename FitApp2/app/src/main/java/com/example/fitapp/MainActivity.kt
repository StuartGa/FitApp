package com.example.fitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitapp.presentation.ui.navigation.AppNavigation
import com.example.fitapp.presentation.ui.screens.onboarding.OnboardingScreen
import com.example.fitapp.presentation.ui.theme.FitAppTheme
import com.example.fitapp.presentation.ui.theme.ThemeMode
import com.example.fitapp.presentation.ui.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            val onboardingCompleted by viewModel.onboardingCompleted.collectAsState(initial = false)

            FitAppTheme(themeMode = ThemeMode.DARK) {
                if (!onboardingCompleted) {
                    OnboardingScreen(
                        onCompleted = { viewModel.completeOnboarding() }
                    )
                } else {
                    AppNavigation()
                }
            }
        }
    }
}
