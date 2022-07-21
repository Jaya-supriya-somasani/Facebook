package com.example.facebook.userprofile

import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.NetworkService
import com.example.facebook.api.request.GetUserProfile
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfilePageViewModel : BaseViewModel() {

    private val profileDetailsStateFlow = MutableStateFlow<GetUserProfile?>(null)
    val profileData: MutableStateFlow<GetUserProfile?> = profileDetailsStateFlow
    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()
    fun getProfileData(userId: String) {
        viewModelScope.launch {
            when (val result = safeApi { NetworkService.apiService.getUserProfile(userId) }) {
                is NetworkResult.Success -> {
                    profileDetailsStateFlow.value = result.data.body()?.data
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

