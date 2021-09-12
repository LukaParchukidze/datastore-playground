package dev.parchukidze.datastoreplayground.data.prefsstore

import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    fun isDarkThemeEnabled(): Flow<Boolean>

    suspend fun toggleDarkTheme()
}