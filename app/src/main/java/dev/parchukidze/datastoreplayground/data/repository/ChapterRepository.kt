package dev.parchukidze.datastoreplayground.data.repository

import dev.parchukidze.datastoreplayground.data.prefsstore.PrefsStore
import dev.parchukidze.datastoreplayground.data.sharedprefs.SharedPrefs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChapterRepository @Inject constructor(private val prefsStore: PrefsStore) {

    fun isDarkThemeEnabled(): Flow<Boolean> = prefsStore.isDarkThemeEnabled()

    suspend fun toggleDarkTheme() = prefsStore.toggleDarkTheme()
}
