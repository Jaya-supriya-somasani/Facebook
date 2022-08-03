package com.example.facebook.di

import com.example.facebook.api.ApiService
import com.example.facebook.login.LoginPageViewModel
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun getViewModel(apiService:ApiService):LoginPageViewModel{
        return LoginPageViewModel(apiService)
    }
}