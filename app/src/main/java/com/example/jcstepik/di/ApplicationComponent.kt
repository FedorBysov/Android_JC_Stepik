package com.example.jcstepik.di

import android.content.Context
import com.example.jcstepik.presentation.ViewModelFactory
import com.example.jcstepik.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ViewModelModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun getComponentScreenFactory():ComponentScreenComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }

}