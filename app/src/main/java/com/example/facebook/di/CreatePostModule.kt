package com.example.facebook.di

import com.example.facebook.api.ApiService
import com.example.facebook.createpost.CreatePostViewModel
import dagger.Module
import dagger.Provides

@Module
class CreatePostModule {
    @Provides
    fun getCreatePostVM(apiService: ApiService):CreatePostViewModel{
        return CreatePostViewModel(apiService)
    }
}