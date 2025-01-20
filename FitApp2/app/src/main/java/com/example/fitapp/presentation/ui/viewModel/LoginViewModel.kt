package com.example.fitapp.presentation.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.fitapp.data.local.datastore.DataStoreManager
import com.example.fitapp.domain.usecases.CreateUserUseCase
import com.example.fitapp.domain.usecases.LoginUseCase
import com.example.fitapp.presentation.ui.mvi.effect.AuthEffect
import com.example.fitapp.presentation.ui.mvi.event.AuthEvent
import com.example.fitapp.presentation.ui.mvi.projectStructure.BaseViewModel
import com.example.fitapp.presentation.ui.mvi.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val dataStoreManager: DataStoreManager,
):BaseViewModel<AuthEvent, AuthState, AuthEffect>() {

    val isLoggedIn = MutableStateFlow(dataStoreManager.readLoggedInState())

    override fun createInitialState(): AuthState = AuthState.Idle


    override fun handleEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> login(event.email, event.password)
            is AuthEvent.Register -> register(event.email, event.password)
        }
    }


    private fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password)
                .onStart { setState { AuthState.Loading } }
                .catch { setEffect { AuthEffect.ShowToast("Login failed") } }
                .collect { result ->
                    result.fold(
                        onSuccess = { user ->
                            setState { AuthState.Success(user) }
                            dataStoreManager.saveSession(true, user.userEmail)
                            setEffect { AuthEffect.NavigateToDashboard }
                        },
                        onFailure = { error ->
                            setState { AuthState.Error(error.message ?: "Unknown error") }
                            setEffect { AuthEffect.ShowToast("Login failed: ${error.message}") }
                        }
                    )
                }
        }
    }

    private fun register(email: String, password: String) {
        viewModelScope.launch {
            createUserUseCase(email, password)
                .onStart { setState { AuthState.Loading } }
                .catch { setEffect { AuthEffect.ShowToast("Registration failed") } }
                .collect { result ->
                    result.fold(
                        onSuccess = { user ->
                            setState { AuthState.Success(user) }
                            dataStoreManager.saveSession(true, user.userEmail)
                            setEffect { AuthEffect.NavigateToDashboard }
                        },
                        onFailure = { error ->
                            setState { AuthState.Error(error.message ?: "Unknown error") }
                            setEffect { AuthEffect.ShowToast("Registration failed: ${error.message}") }
                        }
                    )
                }
        }
    }


}


