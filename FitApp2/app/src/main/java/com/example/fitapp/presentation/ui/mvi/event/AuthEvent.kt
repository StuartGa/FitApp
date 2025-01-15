package com.example.fitapp.presentation.ui.mvi.event

import com.example.fitapp.presentation.ui.mvi.UIEvent

sealed class AuthEvent : UIEvent {

        data class Login(val email: String, val password: String) : AuthEvent()
        data class Register(val email: String, val password: String) : AuthEvent()

}
