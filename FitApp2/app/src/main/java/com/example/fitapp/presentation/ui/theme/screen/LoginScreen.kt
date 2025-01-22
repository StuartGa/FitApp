package com.example.fitapp.presentation.ui.theme.screen

import android.content.Intent
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
import com.example.fitapp.MainActivity
import com.example.fitapp.presentation.ui.component.AuthForm
import com.example.fitapp.presentation.ui.mvi.effect.AuthEffect
import com.example.fitapp.presentation.ui.mvi.state.AuthState
import com.example.fitapp.presentation.ui.viewModel.LoginViewModel

@Composable
fun AuthScreen(
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
    when (state.value) {
        is AuthState.Idle -> AuthForm(onEvent = {

            viewModel.setEvent(it) })
        is AuthState.Loading -> CircularProgressIndicator()
        is AuthState.Error -> {
            AuthForm(onEvent = {
                viewModel.setEvent(it) })
            Text(text = "Error: ${(state.value as AuthState.Error).error}", modifier = Modifier.padding(16.dp))

        }
        is AuthState.Success -> {
            val sendIntent = Intent(context, MainActivity::class.java)
            context.startActivity(sendIntent)
        }
    }
}
