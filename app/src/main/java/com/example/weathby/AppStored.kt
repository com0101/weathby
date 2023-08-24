package com.example.weathby

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppStored(private val dataStore: DataStore<Preferences>) {

    private val DARK_MODE= booleanPreferencesKey("dark_mode")

    val darkModePreferencesFlow: Flow<Boolean> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("error", "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[DARK_MODE]?: false
        }

    suspend fun enableDarkMode(enable: Boolean) {
        dataStore.edit { preferences ->
            // write value
            preferences[DARK_MODE] = enable
        }
    }

}