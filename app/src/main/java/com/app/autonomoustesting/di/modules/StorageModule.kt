package com.app.autonomoustesting.di.modules

import com.app.autonomoustesting.core.localStorage.storage.AppStorage
import com.app.autonomoustesting.core.localStorage.storage.AppStorageImpl
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideAppStorage(appStorage: AppStorageImpl): AppStorage

}