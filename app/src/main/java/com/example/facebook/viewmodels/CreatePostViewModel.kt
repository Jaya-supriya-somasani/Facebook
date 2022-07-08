package com.example.facebook.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.response.ChangePasswordRequest
import com.example.facebook.api.NetworkService
import com.example.facebook.api.request.CreatePost
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreatePostViewModel : BaseViewModel() {
    private val status = Channel<Boolean>()
    val statusEvent = status.receiveAsFlow()
    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()

    fun uploadPost(userId: String, postDesc: String) {
        viewModelScope.launch {
            val result = safeApi {
                NetworkService.apiService.createPost(
                    CreatePost(userId = userId, postDesc = postDesc)
                )
            }
            when (result) {
                is NetworkResult.Success -> {
                    val loginResponse = result.data.body()!!
                    if (loginResponse.status.equals("success", true)) {
                        status.trySend(true)
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