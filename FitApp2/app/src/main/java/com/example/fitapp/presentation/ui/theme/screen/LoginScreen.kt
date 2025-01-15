package com.example.fitapp.presentation.ui.theme.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitapp.presentation.ui.component.AuthForm
import com.example.fitapp.presentation.ui.mvi.effect.AuthEffect
import com.example.fitapp.presentation.ui.mvi.state.AuthState
import com.example.fitapp.presentation.ui.viewModel.LoginViewModel

@Composable
fun AuthScreen(
    onNavigateToHome: () -> Unit
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AuthEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                AuthEffect.NavigateToDashboard -> {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    // UI based on state
    Log.d("LoginScreen", "state -> ${state.value}")
    when (state.value) {
        is AuthState.Idle -> AuthForm(onEvent = {
            Log.d("LoginScreen", "state -> ${it.toString()}")

            viewModel.setEvent(it) })
        is AuthState.Loading -> CircularProgressIndicator()
        is AuthState.Error -> {
            AuthForm(onEvent = {
                Log.d("LoginScreen", "state -> ${it.toString()}")
                viewModel.setEvent(it) })
            Text(text = "Error: ${(state.value as AuthState.Error).error}", modifier = Modifier.padding(16.dp))

        }
        is AuthState.Success -> {
            Log.d("LoginScreen", "state -> ${state.value}")
            onNavigateToHome()
        }
    }
}
