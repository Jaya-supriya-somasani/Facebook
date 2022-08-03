package com.example.facebook.di

import com.example.facebook.api.ApiService
import com.example.facebook.register.RegisterAccountViewModel
import com.example.facebook.userprofile.ProfilePageViewModel
import dagger.Module
import dagger.Provides

@Module
class RegisterModule{
    @Provides
    fun getRegisterAccVM(apiService: ApiService):RegisterAccountViewModel{
        return RegisterAccountViewModel(apiService)
    }
}

@Module
class ProfileModule{
    @Provides
    fun getProfileVM(apiService: ApiService):ProfilePageViewModel{
        return ProfilePageViewModel(apiService)
    }
}