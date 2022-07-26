package com.example.facebook.login

import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.ApiService
import com.example.facebook.api.request.LoginDataClass
import com.example.facebook.api.response.LoginStatus
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPageViewModel @Inject constructor(val apiService: ApiService) : BaseViewModel() {
    val userName = MutableStateFlow("")
    val userPassword = MutableStateFlow("")

    val userNameError = MutableStateFlow("")
    val userPasswordError = MutableStateFlow("")

    private val loginEventChannel = Channel<LoginStatus>()
    val loginEvent = loginEventChannel.receiveAsFlow()

    private val createAccountEventChannel = Channel<Unit>()
    val createAccountEvent = createAccountEventChannel.receiveAsFlow()

    private val forgotEventChannel = Channel<Unit>()
    val forgotPasswordEvent = forgotEventChannel.receiveAsFlow()

    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()


    fun loginBtnClicked() {
        loginValidation()
    }

    fun forgotPasswordClicked() {
        forgotEventChannel.trySend(Unit)
    }

    fun createAccBtnClicked() {
        viewModelScope.launch {
            createAccountEventChannel.send(Unit)
        }
    }

    fun loginValidation() {
        when {
            userName.value.isEmpty() -> {
                userNameError.value = "Please Enter Email or Phone Number"
            }
            userPassword.value.isEmpty() -> {
                userPasswordError.value = "Please Enter Password"
            }
            else -> {
                userNameError.value = ""
                userPasswordError.value = ""

            }
        }
    }


    fun login() {
        val loginRequest = LoginDataClass(username = userName.value, password = userPassword.value)

        viewModelScope.launch {

            when (val loginResult =
                safeApi { apiService.performLogin(loginRequest) }) {
                is NetworkResult.Success -> {
                    loginResult.data.data?.let {
                        it.userName = userName.value
                        loginEventChannel.trySend(it)
                    }
                }
                is NetworkResult.Failure -> {
                    toastEventChannel.trySend(loginResult.message ?: "")
                }
//                is NetworkResult.Exception-> {
//                    toastEventChannel.trySend(loginResult.message?:"")
//                }
            }


        }
    }
}

