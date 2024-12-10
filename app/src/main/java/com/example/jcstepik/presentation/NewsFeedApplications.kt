package com.example.jcstepik.presentation

import android.app.Application
import com.example.jcstepik.di.ApplicationComponent
import com.example.jcstepik.di.DaggerApplicationComponent

class NewsFeedApplications: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}