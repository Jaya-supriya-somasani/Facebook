package com.example.facebook.di

import com.example.facebook.api.ApiService
import com.example.facebook.viewmodels.HomeActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun getMainActivityViewModel(apiService: ApiService):HomeActivityViewModel{
        return HomeActivityViewModel(apiService)
    }
}