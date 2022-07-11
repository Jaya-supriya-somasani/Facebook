package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class SplashScreenViewModel:BaseViewModel() {
    private val profilePgChannel=Channel<Unit>()
    val profilePgEvent=profilePgChannel.receiveAsFlow()

    fun userProfileBtn(){
        profilePgChannel.trySend(Unit)
    }
}