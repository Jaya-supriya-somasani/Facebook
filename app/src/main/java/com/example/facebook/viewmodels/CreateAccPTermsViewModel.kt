package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class CreateAccPTermsViewModel:BaseViewModel() {
    private val termsAndConditionsChannel= Channel<Unit>(Channel.BUFFERED)
    val termsConditionsEvent=termsAndConditionsChannel.receiveAsFlow()

    fun signUpBtn(){
        termsAndConditionsChannel.trySend(Unit)
    }
    fun skipContactsAccessBtn(){
        termsAndConditionsChannel.trySend(Unit)
    }
}