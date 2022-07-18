package com.example.facebook.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(val context: Context) {


    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_DATASTORE")
        val USER_ID = stringPreferencesKey("userId")
        val USER_NAME = stringPreferencesKey("̈userName")
        val IS_LOGGED = booleanPreferencesKey("isLogged")
        val IMMEDIATE = booleanPreferencesKey("immediate")

    }

    // Store user data
//    suspend fun saveToDataStore(data: LoginStatus) {
//        context.dataStore.edit {
//            it[USER_NAME] = data.userName
//            it[USER_ID] = data.userId
//            it[IS_LOGGED] = data.loginStatus
//
//        }
//    }
    suspend fun saveUserName(userName: String) {
        context.dataStore.edit {
            it[USER_NAME] = userName
        }
    }
    suspend fun saveUserId(userId: String) {
        context.dataStore.edit {
            it[USER_ID] = userId
        }
    }
    suspend fun setLoginStatus(status: Boolean) {
        context.dataStore.edit {
            it[IS_LOGGED] = status
        }
    }
    suspend fun setLaunchImmediate(status: Boolean) {
        context.dataStore.edit {
            it[IMMEDIATE] = status
        }
    }

    // Create  flow to get value from datastore
    val userNameFlow: Flow<String> = context.dataStore.data.map {
        it[USER_NAME] ?: ""
    }
    val userIdFlow: Flow<String> = context.dataStore.data.map {
        it[USER_ID] ?: "1"
    }
    var userLoggedStatusFlow: Flow<Boolean> = context.dataStore.data.map {
        it[IS_LOGGED] ?: false
    }
    var launchImmediate: Flow<Boolean> = context.dataStore.data.map {
        it[IMMEDIATE] ?: false
    }
}