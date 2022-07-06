package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccJoinPageViewModel:BaseViewModel() {
    private val createJoinAccChannel=Channel<Unit>(Channel.BUFFERED)
    val createJoinEvent=createJoinAccChannel.receiveAsFlow()


    fun createJoinAccToNextPg(){
        createJoinAccChannel.trySend(Unit)
    }
}