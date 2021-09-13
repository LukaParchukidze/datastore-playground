package dev.parchukidze.datastoreplayground.data.repository

import dev.parchukidze.datastoreplayground.Chapter
import dev.parchukidze.datastoreplayground.data.prefsstore.PrefsStore
import dev.parchukidze.datastoreplayground.data.protostore.ProtoStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChapterRepository @Inject constructor(
    private val prefsStore: PrefsStore,
    private val protoStore: ProtoStore
) {

    fun isDarkThemeEnabled(): Flow<Boolean> = prefsStore.isDarkThemeEnabled()

    suspend fun toggleDarkTheme() = prefsStore.toggleDarkTheme()

    fun getChapterFlow(): Flow<Chapter> = protoStore.getChapterFlow()

    suspend fun updateChapter(name: String, developers: Int, subChapters: Int) =
        protoStore.updateChapter(name, developers, subChapters)

    suspend fun updateDevelopers(developers: Int) = protoStore.updateDevelopers(developers)
}
