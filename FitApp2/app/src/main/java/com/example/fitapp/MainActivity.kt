package com.example.fitapp

import MainActivityState
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitapp.presentation.ui.component.DashboardToolBar
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


            FitAppTheme {
                DashboardScreen()
                when (state.value) {
                    is MainActivityState.Idle -> {
                        Log.d("MainActivity", "state -> ${state.value}")
                        viewModel.setEvent(MainActivityEvent.Loading("email", "password"))
                    }
                    is MainActivityState.Loading -> {
                        Log.d("MainActivity", "state -> ${state.value}")
                    }
                    is MainActivityState.Error -> {
                        Text(text = "Error: ${(state.value as MainActivityState.Error).error}", modifier = Modifier.padding(16.dp))
                        Log.d("MainActivity", "state -> ${state.value}")
                    }
                    is MainActivityState.Success -> {
                        Log.d("MainActivity", "state -> ${state.value}")
                        Text(text = "Success: ${(state.value as MainActivityState.Success).steps}", modifier = Modifier.padding(16.dp))

                    }

                    else -> {}
                }

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    FitAppTheme {
        DashboardScreen()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitAppTheme {
        Greeting("Android")
    }
}