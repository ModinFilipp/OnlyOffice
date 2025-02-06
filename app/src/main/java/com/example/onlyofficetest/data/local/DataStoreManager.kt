package com.example.onlyofficetest.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "auth_prefs"
val Context.datastore by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreManager(private val context: Context) {

    companion object {
        val TOKEN_KEY = stringPreferencesKey("token")
        val EXPIRES_KEY = stringPreferencesKey("expires")
        val BASE_URL_KEY = stringPreferencesKey("url")
        val EMAIL_KEY = stringPreferencesKey("email")
    }

    val tokenFlow: Flow<String?> = context.datastore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }
    val baseUrl: Flow<String?> = context.datastore.data.map { preferences ->
        preferences[BASE_URL_KEY]
    }
    val emailFlow: Flow<String?> = context.datastore.data.map { preferences ->
        preferences[EMAIL_KEY]
    }

    suspend fun saveAuthData(token: String, expires: String, url: String, email: String) {
        context.datastore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[EXPIRES_KEY] = expires
            preferences[BASE_URL_KEY] = url
            preferences[EMAIL_KEY] = email
        }
    }

    suspend fun clearAuthData() {
        context.datastore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(EXPIRES_KEY)
            preferences.remove(BASE_URL_KEY)
            preferences.remove(EMAIL_KEY)
        }
    }

    suspend fun getToken(): Pair<String?, String?> {
        return context.datastore.data.map { preferences ->
            val token = preferences[TOKEN_KEY]
            val expires = preferences[EXPIRES_KEY]
            token to expires
        }.firstOrNull() ?: (null to null)
    }

}