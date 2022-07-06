package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccMobileNumViewModel:BaseViewModel() {
    private val createPhNumChannel=Channel<Unit>(Channel.BUFFERED)
    val createPhNumEent=createPhNumChannel.receiveAsFlow()

    fun createPhNumBtn(){
        createPhNumChannel.trySend(Unit)
    }
}