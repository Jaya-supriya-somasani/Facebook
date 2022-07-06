package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccBdayViewModel:BaseViewModel() {


    private val createUserBdayChannel=Channel<Unit>(Channel.BUFFERED)
    val createUserBdayEvent=createUserBdayChannel.receiveAsFlow()


    fun createUserBdayBtn(){
        createUserBdayChannel.trySend(Unit)
    }
}