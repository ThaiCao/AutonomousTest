package com.app.autonomoustesting.di.components

import androidx.fragment.app.FragmentManager
import com.app.autonomoustesting.di.modules.MainModule
import com.app.autonomoustesting.di.scopes.ActivityScope
import com.app.autonomoustesting.features.main.MainActivity
import com.app.autonomoustesting.shared.ui.activity.BaseActivity
import com.app.autonomoustesting.shared.ui.fragment.BaseFragment
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance supportFragmentManager: FragmentManager): MainComponent
    }

//    fun inject(activity: BaseActivity)
//    fun inject(activity: BaseFragment)

    fun inject(activity: MainActivity)
}