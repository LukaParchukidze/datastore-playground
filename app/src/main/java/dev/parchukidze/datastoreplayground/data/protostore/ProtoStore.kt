package dev.parchukidze.datastoreplayground.data.protostore

import dev.parchukidze.datastoreplayground.Chapter
import kotlinx.coroutines.flow.Flow

interface ProtoStore {
    fun getChapterFlow(): Flow<Chapter>

    suspend fun updateChapter(name: String, developers: Int, subChapters: Int)

    suspend fun updateDevelopers(developers: Int)
}