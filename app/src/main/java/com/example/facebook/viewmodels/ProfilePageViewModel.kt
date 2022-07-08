package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.api.NetworkService
import com.example.facebook.api.request.GetUserProfile
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfilePageViewModel : BaseViewModel() {
    private val userDetailsMutableState = MutableStateFlow<GetUserProfile?>(null)
    var userDetailsStateFlow: StateFlow<GetUserProfile?> = userDetailsMutableState

    init {
        viewModelScope.launch {
            val result = NetworkService.apiService.getUserProfile("2")
            userDetailsMutableState.value = result.body()!!.data!!
        }
    }
}