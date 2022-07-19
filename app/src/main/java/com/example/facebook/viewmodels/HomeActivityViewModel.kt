package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.NetworkService
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeActivityViewModel : BaseViewModel() {

    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()

    private val loginScreenEventChannel = Channel<Boolean>()
    val loginScreenEvent = loginScreenEventChannel.receiveAsFlow()

    private val isLoadingStateFlow=MutableStateFlow(true)
    val isLoading=isLoadingStateFlow.asStateFlow()


    init {
        viewModelScope.launch{
            delay(3000)
            isLoadingStateFlow.value=false
        }
    }

    fun logout(userId: String) {
        viewModelScope.launch {
            when (val result = safeApi { NetworkService.apiService.logOutUser(userId) }) {
                is NetworkResult.Success -> {
                    toastEventChannel.trySend(result.data.body()?.message ?: "")

                    if(!result.data.body()?.data!!.loginStatus) {
                        loginScreenEventChannel.trySend(false)
                    }
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