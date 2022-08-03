package com.example.facebook.di

import com.example.facebook.viewmodels.ForgotPasswordViewModel
import dagger.Module
import dagger.Provides

@Module
class ForgotPswdModule {
    @Provides
    fun getForgotPswdVM():ForgotPasswordViewModel{
        return ForgotPasswordViewModel()
    }
}