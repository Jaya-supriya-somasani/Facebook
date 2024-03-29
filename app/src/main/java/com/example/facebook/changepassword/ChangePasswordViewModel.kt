package com.example.facebook.changepassword

import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.ApiService
import com.example.facebook.api.response.ChangePasswordRequest
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(val apiService: ApiService) : BaseViewModel() {
    private val resetPasswordChannel = Channel<Unit>()
    val resetPasswordEvent = resetPasswordChannel.receiveAsFlow()
    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()

    fun changePassword(userId: Int, newPassword: String, confirmPassword: String) {
        viewModelScope.launch {
            val result = safeApi {
                apiService.changePassword(
                    ChangePasswordRequest(
                        newPassword,
                        confirmPassword
                    ), userId = userId
                )
            }

            when (result) {
                is NetworkResult.Success -> {
                    val loginResponse = result.data.body()!!
                    if (loginResponse.status.equals("success", true)) {
                        resetPasswordChannel.trySend(Unit)
                        toastEventChannel.trySend(loginResponse.message ?: "Success")
                    } else {
                        toastEventChannel.trySend(loginResponse.message ?: "Error")
                    }
                }
                is NetworkResult.Failure -> {
                    toastEventChannel.trySend(result.message ?: "Error")
                }
            }


        }

    }


}