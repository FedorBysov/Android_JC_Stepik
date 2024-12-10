package com.example.jcstepik.di

import android.content.Context
import com.example.jcstepik.data.NewsFeedRepositoryImpl
import com.example.jcstepik.data.network.ApiFactory
import com.example.jcstepik.data.network.ApiService
import com.example.jcstepik.domain.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(
            context: Context
        ): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }

}