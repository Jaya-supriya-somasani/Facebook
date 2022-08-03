package com.example.facebook.di

import com.example.facebook.api.ApiService
import com.example.facebook.viewmodels.HomeActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {
    @Provides
    fun getHomeActivityViewModule(apiService:ApiService):HomeActivityViewModel{
        return HomeActivityViewModel(apiService)
    }
}