package dev.parchukidze.datastoreplayground.data.prefsstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dev.parchukidze.datastoreplayground.data.prefsstore.PrefsStoreImpl.KEYS.DARK_THEME_KEY
import dev.parchukidze.datastoreplayground.util.Constants.PREFS_NAME
import dev.parchukidze.datastoreplayground.util.Constants.STORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.prefsStore: DataStore<Preferences> by preferencesDataStore(
    name = STORE_NAME, produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, PREFS_NAME))
    }
)

class PrefsStoreImpl @Inject constructor(private val datastore: DataStore<Preferences>) :
    PrefsStore {

    override fun isDarkThemeEnabled(): Flow<Boolean> = datastore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[DARK_THEME_KEY] ?: true
    }

    override suspend fun toggleDarkTheme() {
        datastore.edit { preferences ->
            preferences[DARK_THEME_KEY] = !(preferences[DARK_THEME_KEY] ?: true)
        }
    }

    private object KEYS {
        val DARK_THEME_KEY = booleanPreferencesKey("enable_dark_theme")
    }
}