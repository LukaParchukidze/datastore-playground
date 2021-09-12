package dev.parchukidze.datastoreplayground.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.parchukidze.datastoreplayground.util.Constants.PREFS_NAME

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}
