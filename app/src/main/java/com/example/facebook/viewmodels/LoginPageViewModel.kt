package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginPageViewModel : BaseViewModel() {
    val userName = MutableStateFlow("")
    val userPassword = MutableStateFlow("")

    private val loginEventChannel = Channel<Unit>()
    val loginEvent = loginEventChannel.receiveAsFlow()

    private val createAccountEventChannel = Channel<Unit>()
    val createAccountEvent = createAccountEventChannel.receiveAsFlow()

    fun loginBtnClicked() {
        viewModelScope.launch {
            loginEventChannel.send(Unit)
        }
    }

    fun forgotPswdBtnClicked() {

    }

    fun createAccBtnClicked() {
        viewModelScope.launch {
            createAccountEventChannel.send(Unit)
        }
    }
}
