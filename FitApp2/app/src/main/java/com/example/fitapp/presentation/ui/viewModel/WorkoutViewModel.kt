package com.example.fitapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitapp.data.local.database.WorkoutDao
import com.example.fitapp.data.local.datastore.DataStoreManager
import com.example.fitapp.domain.entities.WorkoutEntity
import com.example.fitapp.domain.entities.WorkoutSessionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _workouts = MutableStateFlow<List<WorkoutEntity>>(emptyList())
    val workouts: StateFlow<List<WorkoutEntity>> = _workouts.asStateFlow()

    private val _elapsedSeconds = MutableStateFlow(0L)
    val elapsedSeconds: StateFlow<Long> = _elapsedSeconds.asStateFlow()

    private var currentSessionId: Long = 0
    private var isTimerRunning = false

    init {
        loadWorkouts()
    }

    private fun loadWorkouts() {
        viewModelScope.launch {
            _workouts.value = workoutDao.getAllWorkouts()
        }
    }

    fun startSession() {
        viewModelScope.launch {
            val email = dataStoreManager.readUserId().first() ?: return@launch
            val session = WorkoutSessionEntity(
                workoutId = 0,
                userEmail = email
            )
            currentSessionId = workoutDao.startSession(session)
            isTimerRunning = true
            startTimer()
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (isTimerRunning) {
                delay(1000)
                _elapsedSeconds.value = _elapsedSeconds.value + 1
            }
        }
    }

    fun finishSession() {
        isTimerRunning = false
        viewModelScope.launch {
            if (currentSessionId > 0) {
                val session = WorkoutSessionEntity(
                    id = currentSessionId,
                    workoutId = 0,
                    userEmail = "",
                    completedAt = System.currentTimeMillis(),
                    durationSeconds = _elapsedSeconds.value
                )
                workoutDao.updateSession(session)
            }
            _elapsedSeconds.value = 0
        }
    }
}
