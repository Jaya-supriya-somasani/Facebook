package com.example.facebook.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.facebook.api.response.LoginStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppDataStore(val context: Context) {


    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_DATASTORE")
        val USER_ID = stringPreferencesKey("userId")
        val USER_NAME = stringPreferencesKey("̈userName")
        val IS_LOGGED = booleanPreferencesKey("isLogged")

    }

    // Store user data
    suspend fun saveToDataStore(data: LoginStatus) {
        context.dataStore.edit {
            it[USER_NAME] = data.userName
            it[USER_ID] = data.userId
            it[IS_LOGGED] = data.loginStatus

        }
    }

    // Create  flow to get value from datastore
    val userNameFlow: Flow<String> = context.dataStore.data.map {
        it[USER_NAME] ?: ""
    }
    val userIdFlow: Flow<String> = context.dataStore.data.map {
        it[USER_ID] ?: "1"
    }
    val userLoggedStatusFlow: Flow<Boolean> = context.dataStore.data.map {
        it[IS_LOGGED] ?: false
    }
}