package com.example.fitapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitapp.data.local.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _themeMode = MutableStateFlow(1)
    val themeMode: StateFlow<Int> = _themeMode.asStateFlow()

    private val _unitSystem = MutableStateFlow("metric")
    val unitSystem: StateFlow<String> = _unitSystem.asStateFlow()

    init {
        viewModelScope.launch {
            _themeMode.value = dataStoreManager.readThemeMode().first()
            _unitSystem.value = dataStoreManager.readUnitSystem().first()
        }
    }

    fun cycleTheme() {
        val next = (_themeMode.value + 1) % 3
        _themeMode.value = next
        viewModelScope.launch { dataStoreManager.saveThemeMode(next) }
    }

    fun toggleUnits() {
        val next = if (_unitSystem.value == "metric") "imperial" else "metric"
        _unitSystem.value = next
        viewModelScope.launch { dataStoreManager.saveUnitSystem(next) }
    }
}
