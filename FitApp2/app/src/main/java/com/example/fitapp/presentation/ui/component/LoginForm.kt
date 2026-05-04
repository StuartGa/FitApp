package com.example.fitapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitapp.presentation.ui.mvi.event.AuthEvent

private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$")

@Composable
fun AuthForm(onEvent: (AuthEvent) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginMode by remember { mutableStateOf(true) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (it.isNotEmpty() && !EMAIL_REGEX.matches(it.trim())) {
                    "Invalid email format"
                } else {
                    null
                }
            },
            label = { Text("Email") },
            isError = emailError != null,
            supportingText = emailError?.let { { Text(it) } },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = if (it.isNotEmpty() && it.length < 8) {
                    "Password must be at least 8 characters"
                } else {
                    null
                }
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            supportingText = passwordError?.let { { Text(it) } },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val trimmedEmail = email.trim()
            val isValid = EMAIL_REGEX.matches(trimmedEmail) && password.length >= 8
            if (!isValid) {
                if (!EMAIL_REGEX.matches(trimmedEmail)) emailError = "Invalid email format"
                if (password.length < 8) passwordError = "Password must be at least 8 characters"
                return@Button
            }
            if (isLoginMode) {
                onEvent(AuthEvent.Login(trimmedEmail, password))
            } else {
                onEvent(AuthEvent.Register(trimmedEmail, password))
            }
        }) {
            Text(text = if (isLoginMode) "Login" else "Register")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { isLoginMode = !isLoginMode }) {
            Text(text = if (isLoginMode) "Switch to Register" else "Switch to Login")
        }
    }
}

@Preview
@Composable
fun AuthPreview() {
    AuthForm { }
}
