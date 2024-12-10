package com.example.jcstepik.di

import com.example.jcstepik.domain.entity.FeedPost
import com.example.jcstepik.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ComponentViewModelModule::class
    ]
)
interface ComponentScreenComponent{
    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory{

        fun create(
            @BindsInstance feedPost:FeedPost
        ):ComponentScreenComponent
    }

}