package com.example.fitapp.presentation.ui.mvi.effect

import com.example.fitapp.presentation.ui.mvi.UIEffect

sealed class AuthEffect: UIEffect {
    data class ShowToast(val message: String): AuthEffect()
    object NavigateToDashboard: AuthEffect()
}