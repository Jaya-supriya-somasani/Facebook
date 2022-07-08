package com.example.facebook.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object AppDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "facebookDatastore")

    suspend fun saveUserName(context: Context, userName: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(USER_NAME)] = userName
        }
    }

    suspend fun getUserName(context: Context): Flow<String?> {
        return context.dataStore.data.map {
            it[stringPreferencesKey(USER_NAME)]
        }
    }

    suspend fun saveUserId(context: Context, userId: Int) {
        context.dataStore.edit {
            it[intPreferencesKey(USER_ID)] = userId
        }
    }

    suspend fun getUserId(context: Context): Flow<Int?> {
        return context.dataStore.data.map {
            it[intPreferencesKey(USER_ID)]
        }
    }

    //    companion object {
    const val USER_NAME = "user_name"
    const val USER_ID = "user_id"
//    }
}