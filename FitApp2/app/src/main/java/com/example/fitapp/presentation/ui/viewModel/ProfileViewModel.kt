package com.example.fitapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitapp.data.local.database.GoalDao
import com.example.fitapp.data.local.database.UserProfileDao
import com.example.fitapp.data.local.datastore.DataStoreManager
import com.example.fitapp.domain.entities.GoalEntity
import com.example.fitapp.domain.entities.UserProfileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProfileDao: UserProfileDao,
    private val goalDao: GoalDao,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _profile = MutableStateFlow<UserProfileEntity?>(null)
    val profile: StateFlow<UserProfileEntity?> = _profile.asStateFlow()

    private val _activeGoals = MutableStateFlow<List<GoalEntity>>(emptyList())
    val activeGoals: StateFlow<List<GoalEntity>> = _activeGoals.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val email = dataStoreManager.readUserId().first() ?: return@launch
            val userProfile = userProfileDao.getProfile(email)
            val goals = goalDao.getActiveGoals(email)
            _profile.value = userProfile
            _activeGoals.value = goals
        }
    }

    fun upsertProfile(profile: UserProfileEntity) {
        viewModelScope.launch {
            userProfileDao.upsert(profile)
            _profile.value = profile
        }
    }

    fun setGoal(type: String, targetValue: Double, unit: String, userEmail: String) {
        viewModelScope.launch {
            val existing = goalDao.getGoalByType(userEmail, type)
            if (existing != null) {
                goalDao.deactivate(existing.id)
            }
            val goal = GoalEntity(
                type = type,
                targetValue = targetValue,
                unit = unit,
                userEmail = userEmail
            )
            goalDao.insert(goal)
            loadData()
        }
    }
}
