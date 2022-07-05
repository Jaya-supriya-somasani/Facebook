package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginPageViewModel:BaseViewModel() {
    val userName=MutableStateFlow("")
    val userPassword=MutableStateFlow("")
}