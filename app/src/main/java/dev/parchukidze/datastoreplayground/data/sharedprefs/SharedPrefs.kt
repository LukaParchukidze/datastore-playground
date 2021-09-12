package dev.parchukidze.datastoreplayground.data.sharedprefs

import android.content.SharedPreferences
import androidx.core.content.edit
import dev.parchukidze.datastoreplayground.data.sharedprefs.SharedPrefs.KEYS.KEY_DARK_THEME
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefs @Inject constructor(private val preferences: SharedPreferences) {

    fun isDarkThemeEnabled() = preferences.getBoolean(KEY_DARK_THEME, false)

    fun toggleDarkTheme(enabled: Boolean) {
        preferences.edit { putBoolean(KEY_DARK_THEME, enabled) }
    }

    private object KEYS {
        const val KEY_DARK_THEME = "enable_dark_theme"
    }
}
