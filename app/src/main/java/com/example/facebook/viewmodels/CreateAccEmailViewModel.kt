package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccEmailViewModel:BaseViewModel() {
    private val userEmailChannel=Channel<Unit>(Channel.BUFFERED)
    val userEmailEvent=userEmailChannel.receiveAsFlow()

    private val userEmailSkipChannel=Channel<Unit>(Channel.BUFFERED)
    val userEmailSkipEvent=userEmailSkipChannel.receiveAsFlow()

    fun userEmailBtnNext(){
        userEmailChannel.trySend(Unit)
    }

    fun emailSkipBtn(){
        userEmailSkipChannel.trySend(Unit)
    }
}