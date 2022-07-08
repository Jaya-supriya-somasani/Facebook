package com.example.facebook.viewmodels

import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomePageViewModel : BaseViewModel() {
    private val postDetailsMutableState = MutableStateFlow<List<PostsResponsesItem>>(emptyList())
    var postDetailsStateFlow: StateFlow<List<PostsResponsesItem>> = postDetailsMutableState

//    init {
//        if (postDetailsStateFlow != null) {
//            viewModelScope.launch {
//                val result = NetworkService.apiService.getPosts()
//                postDetailsMutableState.value = result.body()!!.data?.postData ?: emptyList()
//            }
//        }
//    }
}