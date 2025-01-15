package com.example.fitapp.presentation.ui.mvi.state

import com.example.fitapp.domain.UserModel
import com.example.fitapp.presentation.ui.mvi.UIState

sealed class AuthState: UIState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: UserModel) : AuthState()
    data class Error(val error: String) : AuthState()
}