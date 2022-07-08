package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.api.NetworkService
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomePageViewModel : BaseViewModel() {
    private val postDetailsMutableState = MutableStateFlow<List<PostsResponsesItem>>(emptyList())
    var postDetailsStateFlow: StateFlow<List<PostsResponsesItem>> = postDetailsMutableState

    init {
        viewModelScope.launch {
            val result = NetworkService.apiService.getPosts()
            postDetailsMutableState.value = result.body()?.data!!
        }
    }
}