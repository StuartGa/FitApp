package com.example.fitapp.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun labelWithTextVertical(labelText: String){
    var userText by remember{ mutableStateOf("") }

    Column(Modifier.fillMaxWidth()
        .padding(24.dp)) {
        Text(labelText, Modifier.padding(vertical = 8.dp))
        TextField(
            value = userText,
            onValueChange = { userText = it},
        )
    }
}