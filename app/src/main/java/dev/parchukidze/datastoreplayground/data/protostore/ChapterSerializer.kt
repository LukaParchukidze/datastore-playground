package dev.parchukidze.datastoreplayground.data.protostore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import dev.parchukidze.datastoreplayground.Chapter
import java.io.InputStream
import java.io.OutputStream

object ChapterSerializer : Serializer<Chapter> {

    override val defaultValue: Chapter
        get() = Chapter.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Chapter {
        try {
            return Chapter.parseFrom(input)
        } catch (ex: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", ex)
        }
    }

    override suspend fun writeTo(t: Chapter, output: OutputStream) {
        t.writeTo(output)
    }
}