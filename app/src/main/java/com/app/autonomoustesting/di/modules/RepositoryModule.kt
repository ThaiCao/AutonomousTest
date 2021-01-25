package com.app.autonomoustesting.di.modules

import com.app.autonomoustesting.core.repository.AppRepository
import com.app.autonomoustesting.core.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideAppRepository(appRepository: AppRepositoryImpl): AppRepository

}