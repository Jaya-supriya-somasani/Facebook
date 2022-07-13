package com.example.facebook.viewmodels

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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeMainViewModel : BaseViewModel() {
    private val postDetailsMutableState = MutableStateFlow<List<PostsResponsesItem>>(emptyList())
    var postDetailsStateFlow: StateFlow<List<PostsResponsesItem>> = postDetailsMutableState
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
        isRefreshingData.value = !isRefreshingData.value
        viewModelScope.launch {
            getPosts()
            getSuggestFriends()
            isRefreshingData.value = !isRefreshingData.value
        }


    }

}