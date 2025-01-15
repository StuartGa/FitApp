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

@Composable
fun AuthForm(onEvent: (AuthEvent) -> Unit ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginMode by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (isLoginMode) {
                onEvent.invoke(AuthEvent.Login(email, password))
            } else {
                onEvent.invoke(AuthEvent.Register(email, password))
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
fun AuthPreview(){
    AuthForm {  }
}
