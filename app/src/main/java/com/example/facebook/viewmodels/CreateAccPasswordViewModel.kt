package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccPasswordViewModel:BaseViewModel() {
    private val userPswdChannel=Channel<Unit>(Channel.BUFFERED)
    val userPswdEvent=userPswdChannel.receiveAsFlow()

    fun createPswdBtn(){
        userPswdChannel.trySend(Unit)
    }
}