package com.app.autonomoustesting.di.modules

import com.app.autonomoustesting.core.memoryCache.AppMemoryCache
import com.app.autonomoustesting.core.memoryCache.AppMemoryCacheImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MemoryCacheModule {

    @Binds
    abstract fun provideAppMemoryCache(appMemoryCache: AppMemoryCacheImpl): AppMemoryCache

}