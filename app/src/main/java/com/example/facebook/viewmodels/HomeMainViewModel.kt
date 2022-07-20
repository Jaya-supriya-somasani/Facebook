package com.example.facebook.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.NetworkService
import com.example.facebook.api.request.AddNewFriend
import com.example.facebook.api.request.SuggestFriendResponse
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeMainViewModel : BaseViewModel() {
    private val postDetailsMutableState = MutableStateFlow<List<PostsResponsesItem>>(emptyList())
    private val isLikePostChanged = MutableStateFlow(false)

    var postDetailsStateFlow =
        combine(postDetailsMutableState, isLikePostChanged) { list, isLikeChanged ->
            list
        }

    val userId = MutableStateFlow("")
    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()
    private val suggestFriendsListStateFlow =
        MutableStateFlow<List<SuggestFriendResponse>>(emptyList())
    val suggestFriendsList: MutableStateFlow<List<SuggestFriendResponse>> =
        suggestFriendsListStateFlow
    val isRefreshingData = MutableStateFlow(false)

    suspend fun getPosts() {
        when (val result = safeApi { NetworkService.apiService.getPosts(userId.value) }) {
            is NetworkResult.Success -> {
                postDetailsMutableState.value = result.data.data!!
            }
            is NetworkResult.Exception -> {
                toastEventChannel.trySend(result.message ?: "")
            }
        }
    }


    fun onLikeClicked(item: PostsResponsesItem) {
        val likeStatus = !item.likeStatus
        viewModelScope.launch {
            when (val result = safeApi {
                NetworkService.apiService.updateLike(
                    userId.value,
                    item.postId,
                    likeStatus
                )
            }) {
                is NetworkResult.Success -> {
                    Log.d("TAG", "onLikeClicked: before ${item.likesCount}")

                    if (likeStatus) {
                        item.likesCount = ((item.likesCount?.toInt()?:0) + 1).toString()
                    } else {
                        item.likesCount = ((item.likesCount?.toInt()?:0) - 1).toString()
                    }
                    item.likeStatus = likeStatus
                    Log.d("TAG", "onLikeClicked: after ${item.likesCount}")
                    isLikePostChanged.value = !isLikePostChanged.value
                    toastEventChannel.trySend(result.data.body()?.message ?: "")
                }
                is NetworkResult.Exception -> {
                    toastEventChannel.trySend(result.message ?: "")
                }
            }
        }
    }

    fun onDeleteClicked(item: PostsResponsesItem) {
        viewModelScope.launch {
            when (val result = safeApi {
                NetworkService.apiService.deletePost(userId.value, item.postId)
            }) {
                is NetworkResult.Success -> {
                    toastEventChannel.trySend(result.data.body()?.message ?: "")
                }
                is NetworkResult.Exception -> {
                    toastEventChannel.trySend(result.message ?: "")
                }
            }
        }
    }

    suspend fun getSuggestFriends() {
        when (val result =
            safeApi { NetworkService.apiService.getSuggestUser(userId.value) }) {
            is NetworkResult.Success -> {
                suggestFriendsListStateFlow.value = result.data.data!!
            }
            is NetworkResult.Failure -> {
                toastEventChannel.trySend(result.message ?: "")
            }
            is NetworkResult.Exception -> {
                toastEventChannel.trySend(result.message ?: "")
            }
        }
    }

    fun addFriend(item: SuggestFriendResponse) {
        viewModelScope.launch {
            when (val result =
                safeApi {
                    NetworkService.apiService.addFriend(
                        AddNewFriend(
                            item.friendId,
                            userId.value
                        )
                    )
                }) {
                is NetworkResult.Success -> {
                    toastEventChannel.trySend(result.data.body()?.message ?: "")
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

    fun refreshDataFromServer() {
        isRefreshingData.value = true
        Log.d("TAG", "refreshDataFromServer before: ${isRefreshingData.value}")
        viewModelScope.launch {
            getPosts()
            getSuggestFriends()
            isRefreshingData.value = false
            Log.d("TAG", "refreshDataFromServer after: ${isRefreshingData.value}")
//            isRefreshingData.value = !isRefreshingData.value
        }


    }

}