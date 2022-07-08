package com.example.facebook.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.facebook.api.response.ChangePasswordRequest
import com.example.facebook.api.NetworkService
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ChangePasswordViewModel : BaseViewModel() {
    private val resetPasswordChannel = Channel<Unit>()
    val resetPasswordEvent = resetPasswordChannel.receiveAsFlow()
    private val status = Channel<Boolean>()
    val statusEvent = status.receiveAsFlow()
    fun resetPasswordBtn() {
        viewModelScope.launch {
            resetPasswordChannel.send(Unit)
        }
    }

    fun changePassword(userId: String, newpassword: String, conformPassword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = NetworkService.apiService.changePassword(
                ChangePasswordRequest(
                    newpassword,
                    conformPassword
                ), userId = userId
            )
            Log.e("TAG", "changePassword: ${response.message()}")
            if (response.isSuccessful) {
                status.send(true)
            } else {
                status.send(false)

            }


        }

    }


}