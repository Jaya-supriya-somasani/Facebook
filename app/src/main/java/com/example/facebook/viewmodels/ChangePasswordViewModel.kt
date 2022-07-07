package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class ChangePasswordViewModel:BaseViewModel() {
    private val resetPasswordChannel=Channel<Unit>(Channel.BUFFERED)
    val restePasswordEvent=resetPasswordChannel.receiveAsFlow()

    fun resetPasswordBtn(){
        resetPasswordChannel.trySend(Unit)
    }

}