package com.example.facebook.di

import com.example.facebook.api.ApiService
import com.example.facebook.friendslist.FriendsListViewModel
import dagger.Module
import dagger.Provides

@Module
class FriendsListModule {
    @Provides
    fun getFriendsListVM(apiService: ApiService):FriendsListViewModel{
        return FriendsListViewModel(apiService)
    }
}