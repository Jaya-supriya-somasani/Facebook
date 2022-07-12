package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.api.NetworkService
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeMainViewModel : BaseViewModel() {
    private val postDetailsMutableState = MutableStateFlow<List<PostsResponsesItem>>(emptyList())
    var postDetailsStateFlow: StateFlow<List<PostsResponsesItem>> = postDetailsMutableState
    val userId = MutableStateFlow<String>("")

    init {
        viewModelScope.launch {
            val result = NetworkService.apiService.getPosts()
            postDetailsMutableState.value = result.body()?.data!!
        }
    }

    fun onLikeClicked(item: PostsResponsesItem) {
        val likeStatus = !item.likeStatus
        viewModelScope.launch {
            NetworkService.apiService.updateLike(userId.value, item.postId, likeStatus)
        }
    }

    fun onDeleteClicked(item: PostsResponsesItem) {
        viewModelScope.launch {
            NetworkService.apiService.deletePost(userId.value, item.postId)
        }
    }
}