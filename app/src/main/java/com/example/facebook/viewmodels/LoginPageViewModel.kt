package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class LoginPageViewModel : BaseViewModel() {
    val userName = MutableStateFlow("")
    val userPassword = MutableStateFlow("")

    val userNameErrorMsg = MutableStateFlow("")
    val userPswdErrorMsg = MutableStateFlow("")

    private val navigateToCreateAccChannel= Channel<Unit> (Channel.BUFFERED)
    val navigateToCreateAccount=navigateToCreateAccChannel.receiveAsFlow()

    private val navigateToLoginChannel=Channel<Unit>(Channel.BUFFERED)
    val navigateToLoginScreenToHome=navigateToLoginChannel.receiveAsFlow()


    fun loginBtnClicked() {
        loginChecking()
    }

    private fun loginChecking() {
        if (userName.value.isEmpty()) {
            userNameErrorMsg.value = "Please Enter Email or Phone Number"
        } else if (userPassword.value.isEmpty()) {
            userPswdErrorMsg.value = "Please Enter Password"
        } else {
            login()
        }

    }

    fun forgotPswdBtnClicked() {

    }

    fun createAccBtnClicked() {
        navigateToCreateAccChannel.trySend(Unit)
    }

    private fun login() {
        navigateToLoginChannel.trySend(Unit)
    }
}
fun <T> StateFlow<T>.sendValues(t:T){
    Channel<T>(Channel.BUFFERED).trySend(t)
}
suspend fun <T> ConflatedChannels(): StateFlow<T> = Channel<T>(Channel.BUFFERED).receiveAsFlow().stateIn(
    CoroutineScope(Dispatchers.IO)
)