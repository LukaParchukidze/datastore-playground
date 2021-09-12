package dev.parchukidze.datastoreplayground.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.parchukidze.datastoreplayground.data.prefsstore.PrefsStore
import dev.parchukidze.datastoreplayground.data.prefsstore.PrefsStoreImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class StoreModule {

    @Binds
    abstract fun bindPrefsStore(prefsStoreImpl: PrefsStoreImpl): PrefsStore
}