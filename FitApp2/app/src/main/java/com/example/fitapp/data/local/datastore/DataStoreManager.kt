package com.example.fitapp.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_session")

class DataStoreManager(context: Context) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val THEME_MODE = intPreferencesKey("theme_mode")
        val DAILY_STEP_GOAL = intPreferencesKey("daily_step_goal")
        val DAILY_CALORIE_GOAL = intPreferencesKey("daily_calorie_goal")
        val WORKOUTS_PER_WEEK_GOAL = intPreferencesKey("workouts_per_week_goal")
        val UNIT_SYSTEM = stringPreferencesKey("unit_system")
    }

    suspend fun saveSession(isLoggedIn: Boolean, userEmail: String) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
            preferences[USER_EMAIL] = userEmail
        }
    }

    fun readLoggedInState(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }
    }

    fun readUserId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_EMAIL]
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = false
            preferences[USER_EMAIL] = ""
        }
    }

    suspend fun setOnboardingCompleted() {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = true
        }
    }

    fun isOnboardingCompleted(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[ONBOARDING_COMPLETED] ?: false
        }
    }

    suspend fun saveThemeMode(mode: Int) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE] = mode
        }
    }

    fun readThemeMode(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[THEME_MODE] ?: 1
        }
    }

    suspend fun saveStepGoal(steps: Int) {
        dataStore.edit { preferences ->
            preferences[DAILY_STEP_GOAL] = steps
        }
    }

    fun readStepGoal(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[DAILY_STEP_GOAL] ?: 10000
        }
    }

    suspend fun saveCalorieGoal(calories: Int) {
        dataStore.edit { preferences ->
            preferences[DAILY_CALORIE_GOAL] = calories
        }
    }

    fun readCalorieGoal(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[DAILY_CALORIE_GOAL] ?: 2000
        }
    }

    suspend fun saveWorkoutsPerWeekGoal(count: Int) {
        dataStore.edit { preferences ->
            preferences[WORKOUTS_PER_WEEK_GOAL] = count
        }
    }

    fun readWorkoutsPerWeekGoal(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[WORKOUTS_PER_WEEK_GOAL] ?: 4
        }
    }

    suspend fun saveUnitSystem(unit: String) {
        dataStore.edit { preferences ->
            preferences[UNIT_SYSTEM] = unit
        }
    }

    fun readUnitSystem(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[UNIT_SYSTEM] ?: "metric"
        }
    }
}
