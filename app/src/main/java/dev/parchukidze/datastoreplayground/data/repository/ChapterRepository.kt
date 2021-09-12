package dev.parchukidze.datastoreplayground.data.repository

import dev.parchukidze.datastoreplayground.data.sharedprefs.SharedPrefs
import javax.inject.Inject

class ChapterRepository @Inject constructor(private val sharedPrefs: SharedPrefs) {

    fun isDarkThemeEnabled(): Boolean = sharedPrefs.isDarkThemeEnabled()

    fun toggleDarkTheme(enabled: Boolean) = sharedPrefs.toggleDarkTheme(enabled)
}
