package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.NetworkService
import com.example.facebook.api.request.RegisterUser
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterAccountViewModel : BaseViewModel() {
    private val createAccountChannel = Channel<Unit>(Channel.BUFFERED)
    val createAccountEvent = createAccountChannel.receiveAsFlow()
    val userName = MutableStateFlow("")
    val userEmail = MutableStateFlow("")
    val userPassword = MutableStateFlow("")
    val userDob = MutableStateFlow("")
    val userGender = MutableStateFlow("")
    val userConfirmPassword = MutableStateFlow("")
    val loginStatus = MutableStateFlow(false)


    fun signupBtn() {
        val registerReq = RegisterUser(
            userName = userName.value,
            mail = userEmail.value,
            userPassword = userPassword.value,
            userDob = userDob.value,
            gender = userGender.value,
            confirmPswd = userConfirmPassword.value, loginStatus = loginStatus.value
        )
        viewModelScope.launch {
            val registerResult = safeApi { NetworkService.apiService().registerUser(registerReq) }

            when (registerResult) {
                is NetworkResult.Success -> {
                    createAccountChannel.trySend(Unit)
                }
            }
        }

//        createAccountChannel.trySend(Unit)
    }
}