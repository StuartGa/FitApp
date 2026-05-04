package com.example.fitapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitapp.data.local.database.WorkoutDao
import com.example.fitapp.data.local.datastore.DataStoreManager
import com.example.fitapp.domain.entities.WorkoutSessionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _recentSessions = MutableStateFlow<List<WorkoutSessionEntity>>(emptyList())
    val recentSessions: StateFlow<List<WorkoutSessionEntity>> = _recentSessions.asStateFlow()

    init {
        loadSessions()
    }

    private fun loadSessions() {
        viewModelScope.launch {
            val email = dataStoreManager.readUserId().first() ?: return@launch
            _recentSessions.value = workoutDao.getRecentSessions(email)
        }
    }
}
