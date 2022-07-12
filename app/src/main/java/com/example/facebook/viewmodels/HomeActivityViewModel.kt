package com.example.facebook.viewmodels

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.NetworkService
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeActivityViewModel : BaseViewModel() {

    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()

    private val loginScreenEventChannel = Channel<Boolean>()
    val loginScreenEvent = loginScreenEventChannel.receiveAsFlow()

    fun logout(userId: String) {
        viewModelScope.launch {
            viewModelScope.launch {
                when (val result = safeApi { NetworkService.apiService.logOutUser(userId) }) {
                    is NetworkResult.Success -> {
                        toastEventChannel.trySend(result.data.body()?.message ?: "")
                        loginScreenEventChannel.trySend(result.data.body()?.data!!.loginStatus)
                    }
                    is NetworkResult.Failure -> {
                        toastEventChannel.trySend(result.message ?: "")
                    }
                    is NetworkResult.Exception -> {
                        toastEventChannel.trySend(result.message ?: "")
                    }
                }

            }


        }
    }

}