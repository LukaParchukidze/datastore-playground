package dev.parchukidze.datastoreplayground.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.parchukidze.datastoreplayground.data.prefsstore.prefsStore
import dev.parchukidze.datastoreplayground.util.Constants.PREFS_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providePreferencesDatastore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.prefsStore
}
