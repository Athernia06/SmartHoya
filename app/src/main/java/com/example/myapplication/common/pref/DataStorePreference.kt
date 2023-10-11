package com.example.myapplication.common.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "application")

class DataStorePreference constructor(private val dataStore: DataStore<Preferences>) {

    fun getToken() : Flow<String> {
        return dataStore.data.map { preference ->
            preference[TOKEN] ?: ""
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preference ->
            preference[TOKEN] = token
        }
    }

    fun getUsername() : Flow<String> {
        return dataStore.data.map { preference ->
            preference[USERNAME] ?: ""
        }
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit { preference ->
            preference[USERNAME] = username
        }
    }

    fun getName() : Flow<String> {
        return dataStore.data.map { preference ->
            preference[NAME] ?: ""
        }
    }

    suspend fun saveName(name: String) {
        dataStore.edit { preference ->
            preference[NAME] = name
        }
    }

    fun getEmail() : Flow<String> {
        return dataStore.data.map { preference ->
            preference[EMAIL] ?: ""
        }
    }

    suspend fun saveEmail(email: String) {
        dataStore.edit { preference ->
            preference[EMAIL] = email
        }
    }

    suspend fun logout() {
        dataStore.edit { preference ->
            preference.clear()
        }
    }

    companion object {
        val TOKEN = stringPreferencesKey("token")
        val USERNAME = stringPreferencesKey("username")
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
    }
}