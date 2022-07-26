package com.example.facebook.register

import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.ApiService
import com.example.facebook.api.request.RegisterUser
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterAccountViewModel @Inject constructor(val apiService: ApiService): BaseViewModel() {
    private val createAccountChannel = Channel<Unit>(Channel.BUFFERED)
    val createAccountEvent = createAccountChannel.receiveAsFlow()
    val userName = MutableStateFlow("")
    val userEmail = MutableStateFlow("")
    val userPassword = MutableStateFlow("")
    val userDob = MutableStateFlow("")
    val userGender = MutableStateFlow("")
    val termsSelectionFlow = MutableStateFlow(false)
    val userConfirmPassword = MutableStateFlow("")
    val loginStatus = MutableStateFlow(false)
    val maleGender = MutableStateFlow(false)
    val femaleGender = MutableStateFlow(false)
    val userNameError = MutableStateFlow("")
    val userEmailError = MutableStateFlow("")
    val userPasswordError = MutableStateFlow("")
    val userDobError = MutableStateFlow("")
    val userGenderError = MutableStateFlow("")
    val userConfirmPasswordError = MutableStateFlow("")

    private val toastEventChannel = Channel<String>(Channel.BUFFERED)
    val toastEvent = toastEventChannel.receiveAsFlow()


    fun passwordValidation() {
        if (userConfirmPassword.value.equals(userPassword.value)) {
            userNameError.value = ""
            userPasswordError.value = ""
            userEmailError.value = ""
            userConfirmPasswordError.value = ""
            userDobError.value = ""
            userGenderError.value = ""
        } else if (userConfirmPassword.value != userPassword.value) {
            userConfirmPasswordError.value = "Password didn't match"
        }
        signupBtn()

    }

    private fun signupBtn() {
        if (maleGender.value) {
            userGender.value = "Male"
        } else {
            userGender.value = "Female"
        }
        val registerReq = RegisterUser(
            userName = userName.value,
            mail = userEmail.value,
            userPassword = userPassword.value,
            userDob = userDob.value,
            gender = userGender.value,
            confirmPswd = userConfirmPassword.value,
            loginStatus = loginStatus.value
        )
        viewModelScope.launch {

            when (val registerResult =
                safeApi {apiService.registerUser(registerReq) }) {
                is NetworkResult.Success -> {
                    if (registerResult.data.body()?.status?.equals("success") == true) {
                        createAccountChannel.trySend(Unit)
                    }
                    toastEventChannel.trySend(registerResult.data.body()?.message ?: "")
                }
                is NetworkResult.Failure -> {
                    toastEventChannel.trySend(registerResult.message ?: "")

                }
                else -> {
                    toastEventChannel.trySend("skipped")
                }
            }
        }
//        userGender.

//        createAccountChannel.trySend(Unit)
    }

}