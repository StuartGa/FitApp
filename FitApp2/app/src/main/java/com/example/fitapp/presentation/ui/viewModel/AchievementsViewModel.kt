package com.example.fitapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitapp.data.local.database.WorkoutDao
import com.example.fitapp.data.local.datastore.DataStoreManager
import com.example.fitapp.domain.entities.AchievementEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val workoutDao: WorkoutDao
) : ViewModel() {

    private val _unlockedAchievements = MutableStateFlow<List<AchievementEntity>>(emptyList())
    val unlockedAchievements: StateFlow<List<AchievementEntity>> = _unlockedAchievements.asStateFlow()

    private val _currentStreak = MutableStateFlow(0)
    val currentStreak: StateFlow<Int> = _currentStreak.asStateFlow()

    init {
        loadAchievements()
    }

    private fun loadAchievements() {
        viewModelScope.launch {
            val email = dataStoreManager.readUserId().first() ?: return@launch
            val sessions = workoutDao.getRecentSessions(email)
            val sessionCount = sessions.size
            _currentStreak.value = calculateStreak(sessions)

            val unlocked = mutableListOf<AchievementEntity>()
            if (sessionCount >= 1) unlocked.add(createAchievement(email, "first_workout", "First Steps", "Complete your first workout"))
            if (sessionCount >= 5) unlocked.add(createAchievement(email, "workout_5", "Getting Stronger", "Complete 5 workouts"))
            if (sessionCount >= 10) unlocked.add(createAchievement(email, "workout_10", "Dedicated", "Complete 10 workouts"))
            _unlockedAchievements.value = unlocked
        }
    }

    private fun calculateStreak(sessions: List<*>): Int {
        return minOf(sessions.size, 7)
    }

    private fun createAchievement(email: String, key: String, title: String, description: String) = AchievementEntity(
        userEmail = email, achievementKey = key, title = title, description = description
    )
}
