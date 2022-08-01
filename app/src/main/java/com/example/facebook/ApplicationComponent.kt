package com.example.facebook

import com.example.facebook.activity.MainActivity
import com.example.facebook.api.NetworkService
import com.example.facebook.datastore.AppDataStore
import dagger.Component

@Component(modules = [NetworkService::class,AppDataStore::class])
interface ApplicationComponent{
    fun inject(mainActivity: MainActivity)
}