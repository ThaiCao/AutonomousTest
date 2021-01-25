package com.app.autonomoustesting.di.components

import android.content.Context
import com.app.autonomoustesting.BaseApplication
import com.app.autonomoustesting.di.ViewModelBuilder
import com.app.autonomoustesting.di.modules.*
import com.app.autonomoustesting.shared.broadcast.NetworkStateChangeReceiver
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilder::class,
        RepositoryModule::class,
        StorageModule::class,
        ApiModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        MemoryCacheModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: BaseApplication)

    fun mainComponent(): MainComponent.Factory

    fun inject(networkStateChangeReceiver: NetworkStateChangeReceiver)
}