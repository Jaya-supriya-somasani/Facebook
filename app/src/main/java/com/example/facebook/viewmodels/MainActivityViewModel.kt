package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.NetworkService
import com.example.facebook.api.request.LoginDataClass
import com.example.facebook.api.response.LoginRequest
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : BaseViewModel() {
    val userName = MutableStateFlow("")
    val userPassword = MutableStateFlow("")
    val userNameError = MutableStateFlow("")
    val userPasswordError = MutableStateFlow("")

    private val loginDetailsMutableState = MutableStateFlow<LoginDataClass?>(null)
    val loginDetailsStateFlow: StateFlow<LoginDataClass?> = loginDetailsMutableState

    private val loginEventChannel = Channel<Unit>()
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
                login()
            }
        }
    }

    private fun login() {

        val loginRequest =
            LoginRequest(userEmail = userName.value, userPassword = userPassword.value)

        viewModelScope.launch {
            val result = safeApi { NetworkService.apiService.signIn(loginRequest) }

            when (result) {
                is NetworkResult.Success -> {
                    val loginResponse = result.data.body()!!
                    if (loginResponse.status.equals("success", true)) {
                        loginEventChannel.trySend(Unit)
                        toastEventChannel.trySend(loginResponse.message ?: "Success")
                    } else {
                        toastEventChannel.trySend(loginResponse.message ?: "Error")
                    }
                }
                is NetworkResult.Error -> {
                    toastEventChannel.trySend(result.message ?: "Error")
                }
            }


        }
    }
}

