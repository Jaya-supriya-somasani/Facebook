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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeMainViewModel : BaseViewModel() {
    val postDetailsMutableState = MutableStateFlow<List<PostsResponsesItem>>(emptyList())
    private val likePostChangeChanel = Channel<Int>()
    val likePostChangeEvent = likePostChangeChanel.receiveAsFlow()

    val userId = MutableStateFlow("")

    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()

    private val suggestFriendsListStateFlow =
        MutableStateFlow<List<SuggestFriendResponse>>(emptyList())

    private val addFriendChannel=Channel<Int>()
    val addFriendEvent=addFriendChannel.receiveAsFlow()

    val suggestFriendsList: MutableStateFlow<List<SuggestFriendResponse>> = suggestFriendsListStateFlow
    val isRefreshingData = MutableStateFlow(false)

    private val deletePostChangeChannel = Channel<Int>()
    val deletePostChangeEvent=deletePostChangeChannel.receiveAsFlow()

    val userPostFlow = userId.map {
        getPosts()
    }

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


    fun onLikeClicked(item: PostsResponsesItem, position: Int) {
        val likeStatus = !item.likeStatus
        viewModelScope.launch {
            val result = safeApi {
                NetworkService.apiService.updateLike( userId.value, item.postId, likeStatus)
            }

            when (result) {
                is NetworkResult.Success -> {
                    Log.d("TAG", "onLikeClicked: before ${item.likesCount}")

                    val likeResponse = result.data.data!!
                    item.likesCount = likeResponse.count
                    item.likeStatus = likeResponse.likeStatus.toBoolean()
                    Log.d("TAG", "onLikeClicked: after ${item.likesCount}")
                    likePostChangeChanel.trySend(position)
                    toastEventChannel.trySend(result.data.message ?: "")
                }
                is NetworkResult.Exception -> {
                    toastEventChannel.trySend(result.message ?: "")
                }
                else -> {
                    //toastEventChannel.trySend(result.message ?: "")
                }
            }
        }
    }

    fun onDeleteClicked(item: PostsResponsesItem, position: Int) {
        viewModelScope.launch {
            when (val result = safeApi {
                NetworkService.apiService.deletePost(userId.value, item.postId)

            }) {
                is NetworkResult.Success -> {
                    deletePostChangeChannel.trySend(position)
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

//    fun removeSugges

    fun addFriend(item: SuggestFriendResponse, position: Int) {
        viewModelScope.launch {
            when (val result =
                safeApi { NetworkService.apiService.addFriend( AddNewFriend( item.friendId, userId.value ))
                }) {
                is NetworkResult.Success -> {
                    toastEventChannel.trySend(result.data.body()?.message ?: "")
                    val list = suggestFriendsListStateFlow.value as ArrayList
                    // Removed item after adding the suggested friend
                    list.removeAt(position)
                    addFriendChannel.trySend(position)
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