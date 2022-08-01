package com.example.facebook.activity

import android.app.Application
import com.example.facebook.ApplicationComponent
import com.example.facebook.DaggerApplicationComponent

class DaggerApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}