package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateAccountViewModel:BaseViewModel() {
    val userName = MutableStateFlow("")
    val userPassword = MutableStateFlow("")
    val userEmail=MutableStateFlow("")
    val userPhoneNumber=MutableStateFlow("")

    private val createAccountEventChannel = Channel<Unit>()
    val createAccountEvent = createAccountEventChannel.receiveAsFlow()

    fun signupBtn() {
        viewModelScope.launch {
            createAccountEventChannel.send(Unit)
        }
    }
}