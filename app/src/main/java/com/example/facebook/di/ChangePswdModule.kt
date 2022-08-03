package com.example.facebook.di

import com.example.facebook.api.ApiService
import com.example.facebook.changepassword.ChangePasswordViewModel
import dagger.Module
import dagger.Provides

@Module
class ChangePswdModule {
    @Provides
    fun getChangePswdVM(apiService: ApiService):ChangePasswordViewModel{
        return ChangePasswordViewModel(apiService)
    }
}