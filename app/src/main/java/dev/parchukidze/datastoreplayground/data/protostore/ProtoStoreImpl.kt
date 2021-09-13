package dev.parchukidze.datastoreplayground.data.protostore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dev.parchukidze.datastoreplayground.Chapter
import dev.parchukidze.datastoreplayground.util.Constants.PROTO_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

val Context.protoStore: DataStore<Chapter> by dataStore(
    fileName = PROTO_NAME,
    serializer = ChapterSerializer
)

class ProtoStoreImpl @Inject constructor(private val protoStore: DataStore<Chapter>) : ProtoStore {

    override fun getChapterFlow(): Flow<Chapter> = protoStore.data.catch { exception ->
        if (exception is IOException) {
            emit(Chapter.getDefaultInstance())
        } else {
            throw exception
        }
    }

    override suspend fun updateChapter(name: String, developers: Int, subChapters: Int) {
        protoStore.updateData { preferences ->
            preferences.toBuilder()
                .setName(name)
                .setDevelopers(developers)
                .setSubChapters(subChapters)
                .build()
        }
    }

    override suspend fun updateDevelopers(developers: Int) {
        protoStore.updateData { preference ->
            preference.toBuilder()
                .setDevelopers(developers)
                .build()
        }
    }
}