package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccountViewModel:BaseViewModel() {
    private val createAccountChannel=Channel<Unit>(Channel.BUFFERED)
    val createAccountEvent=createAccountChannel.receiveAsFlow()
    val userName=MutableStateFlow("")
    val userPhoneNumber=MutableStateFlow("")
    val userEmail=MutableStateFlow("")
    val userPassword=MutableStateFlow("")
    val userDob=MutableStateFlow("")
    val userGender=MutableStateFlow("")


    fun signupBtn(){
        createAccountChannel.trySend(Unit)
    }
}