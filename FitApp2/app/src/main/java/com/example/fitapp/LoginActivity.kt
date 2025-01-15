package com.example.fitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.fitapp.presentation.ui.theme.screen.AuthScreen
import com.example.fitapp.presentation.ui.viewModel.LoginViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
@AndroidEntryPoint
class LoginActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

        setContent{
            Scaffold(Modifier.fillMaxSize()){ padding ->
                AuthScreen( onClick())
            }
        }

    }

    fun onClick() = {}



}