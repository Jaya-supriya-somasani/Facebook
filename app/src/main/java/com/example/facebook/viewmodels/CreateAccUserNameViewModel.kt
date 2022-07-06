package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccUserNameViewModel:BaseViewModel() {
    private val createAccUserChannel=Channel<Unit>(Channel.BUFFERED)
    val createUserNameEvent=createAccUserChannel.receiveAsFlow()


    fun createAccUserNameBtn(){
        createAccUserChannel.trySend(Unit)
    }
}