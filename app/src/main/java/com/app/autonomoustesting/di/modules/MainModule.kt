package com.app.autonomoustesting.di.modules

import androidx.lifecycle.ViewModel
import com.app.autonomoustesting.di.ViewModelKey
import com.app.autonomoustesting.features.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}