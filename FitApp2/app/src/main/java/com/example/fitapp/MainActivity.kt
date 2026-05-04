package com.example.fitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitapp.presentation.ui.mvi.event.MainActivityEvent
import com.example.fitapp.presentation.ui.mvi.state.MainActivityState
import com.example.fitapp.presentation.ui.screens.DashboardScreen
import com.example.fitapp.presentation.ui.theme.FitAppTheme
import com.example.fitapp.presentation.ui.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            val state = viewModel.uiState.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.setEvent(MainActivityEvent.Loading("", ""))
            }

            FitAppTheme {
                DashboardScreen()
                when (state.value) {
                    is MainActivityState.Idle -> {}
                    is MainActivityState.Loading -> {}
                    is MainActivityState.Error -> {
                        Text(
                            text = "Error: ${(state.value as MainActivityState.Error).error}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    is MainActivityState.Success -> {
                        Text(
                            text = "Steps: ${(state.value as MainActivityState.Success).steps}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}
