package com.example.facebook.viewmodels

import com.example.facebook.api.request.ProfilePage
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ProfilePageViewModel:BaseViewModel() {

    private val profileDetailsStateFlow=MutableStateFlow<ProfilePage?>(null)


}