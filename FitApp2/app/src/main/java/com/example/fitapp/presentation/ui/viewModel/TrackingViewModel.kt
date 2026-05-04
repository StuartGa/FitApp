package com.example.fitapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackingViewModel @Inject constructor() : ViewModel() {

    private val _isTracking = MutableStateFlow(false)
    val isTracking: StateFlow<Boolean> = _isTracking.asStateFlow()

    private val _elapsedSeconds = MutableStateFlow(0L)
    val elapsedSeconds: StateFlow<Long> = _elapsedSeconds.asStateFlow()

    private val _distanceMeters = MutableStateFlow(0.0)
    val distanceMeters: StateFlow<Double> = _distanceMeters.asStateFlow()

    private val _currentSpeed = MutableStateFlow(0.0)
    val currentSpeed: StateFlow<Double> = _currentSpeed.asStateFlow()

    private var timerRunning = false

    fun startTracking() {
        _isTracking.value = true
        timerRunning = true
        viewModelScope.launch {
            while (timerRunning) {
                delay(1000)
                _elapsedSeconds.value = _elapsedSeconds.value + 1
                _distanceMeters.value += _currentSpeed.value / 3.6
                _currentSpeed.value = (6.0 + Math.random() * 4.0)
            }
        }
    }

    fun stopTracking() {
        timerRunning = false
        _isTracking.value = false
    }

    fun resetTracking() {
        _elapsedSeconds.value = 0
        _distanceMeters.value = 0.0
        _currentSpeed.value = 0.0
    }
}
