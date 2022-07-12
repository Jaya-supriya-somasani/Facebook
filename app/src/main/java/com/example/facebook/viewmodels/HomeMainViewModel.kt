package com.example.facebook.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.NetworkService
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeMainViewModel : BaseViewModel() {
    private val postDetailsMutableState = MutableStateFlow<List<PostsResponsesItem>>(emptyList())
    var postDetailsStateFlow: StateFlow<List<PostsResponsesItem>> = postDetailsMutableState
    val userId = MutableStateFlow("")
    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()


    fun getPosts() {
        viewModelScope.launch {
            when (val result = safeApi { NetworkService.apiService.getPosts(userId.value) }) {
                is NetworkResult.Success -> {
                    postDetailsMutableState.value = result.data.data!!
                }
                is NetworkResult.Exception -> {
                    toastEventChannel.trySend(result.message ?: "")

                }
            }
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