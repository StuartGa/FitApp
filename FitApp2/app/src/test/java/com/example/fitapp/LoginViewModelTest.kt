package com.example.fitapp

import com.example.fitapp.domain.usecases.CreateUserUseCase
import com.example.fitapp.domain.usecases.LoginUseCase
import com.example.fitapp.presentation.ui.mvi.effect.AuthEffect
import com.example.fitapp.presentation.ui.mvi.event.AuthEvent
import com.example.fitapp.presentation.ui.mvi.state.AuthState
import com.example.fitapp.presentation.ui.viewModel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var createUserUseCase: CreateUserUseCase
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        loginUseCase = mock()
        createUserUseCase = mock()
        viewModel = LoginViewModel(loginUseCase, createUserUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Idle`() {
        assertEquals(AuthState.Idle, viewModel.uiState.value)
    }

    @Test
    fun `invalid email shows validation error`() = runTest {
        viewModel.setEvent(AuthEvent.Login("invalid", "password123"))
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is AuthState.Idle)
    }

    @Test
    fun `short password shows validation error`() = runTest {
        viewModel.setEvent(AuthEvent.Login("test@email.com", "123"))
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is AuthState.Idle)
    }

    @Test
    fun `valid login triggers loading state`() = runTest {
        val email = "test@email.com"
        val password = "password123"
        whenever(loginUseCase.invoke(email, password)).thenReturn(flow { })

        viewModel.setEvent(AuthEvent.Login(email, password))
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is AuthState.Loading)
    }

    @Test
    fun `valid register triggers loading state`() = runTest {
        val email = "test@email.com"
        val password = "password123"
        whenever(createUserUseCase.invoke(email, password)).thenReturn(flow { })

        viewModel.setEvent(AuthEvent.Register(email, password))
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is AuthState.Loading)
    }
}
