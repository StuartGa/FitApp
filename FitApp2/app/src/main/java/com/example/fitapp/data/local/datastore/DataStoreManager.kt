    package com.example.fitapp.data.local.datastore

    import android.content.Context
    import androidx.datastore.core.DataStore
    import androidx.datastore.preferences.core.Preferences
    import androidx.datastore.preferences.core.booleanPreferencesKey
    import androidx.datastore.preferences.core.edit
    import androidx.datastore.preferences.core.stringPreferencesKey
    import androidx.datastore.preferences.preferencesDataStore
    import kotlinx.coroutines.flow.Flow
    import kotlinx.coroutines.flow.map

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_session")

    class DataStoreManager(Context: Context) {

        private val dataStore: DataStore<Preferences> = Context.dataStore

        companion object {

            val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
            val USER_EMAIL = stringPreferencesKey("user_email")

        }


       suspend fun saveSession(isLoggedIn: Boolean, userEmail: String) {
            dataStore.edit { preferences ->
                preferences[IS_LOGGED_IN] = isLoggedIn
                preferences[USER_EMAIL] = userEmail
            }
        }

        fun readLoggedInState(): Flow<Boolean> {
            return dataStore.data
                .map { preferences ->
                    preferences[IS_LOGGED_IN] ?: false
                }
        }

        fun readUserId(): Flow<String?> {
            return dataStore.data
                .map { preferences ->
                    preferences[USER_EMAIL]
                }
        }

        suspend fun clearSession() {
            dataStore.edit { preferences ->
                preferences[IS_LOGGED_IN] = false
                preferences[USER_EMAIL] = ""
            }
        }




        }
