package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccGenderViewModel:BaseViewModel() {

    private val createAccUserGenderChannel=Channel<Unit>(Channel.BUFFERED)
    val userGenderEvent=createAccUserGenderChannel.receiveAsFlow()


    fun createGenderBtn(){
        createAccUserGenderChannel.trySend(Unit)
    }
}