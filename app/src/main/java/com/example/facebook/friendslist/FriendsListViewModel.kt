package com.example.facebook.friendslist

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.facebook.NetworkResult
import com.example.facebook.api.NetworkService
import com.example.facebook.api.request.FriendDetailResponse
import com.example.facebook.safeApi
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class FriendsListViewModel : BaseViewModel() {
    private val friendsListStateFlow = MutableStateFlow<List<FriendDetailResponse>>(emptyList())
    val friendsList: MutableStateFlow<List<FriendDetailResponse>> = friendsListStateFlow
    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()
    //  private val removeFriendChannel = Channel<Int>()
    //  val removeFriendsEvent = removeFriendChannel.receiveAsFlow()

    val totalFriendsList = MutableStateFlow(0)


    fun getFriendsList(userId: String) {
        viewModelScope.launch {
            when (val result = safeApi { NetworkService.apiService.getFriendsList(userId) }) {
                is NetworkResult.Success -> {
                    friendsListStateFlow.value = result.data.data!!
                    totalFriendsList.value = friendsListStateFlow.value.size

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


    fun removeFriend(item: FriendDetailResponse, userId: String, count: Int) {
        viewModelScope.launch {
            when (val result =
                safeApi { NetworkService.apiService.deleteFriend(item.friendId, userId) }) {
                is NetworkResult.Success -> {
                    totalFriendsList.value = count
                    toastEventChannel.trySend(result.data.message() ?: "")
                    Log.e("TAG", "removeFriend:-${friendsList.value.size} ")
                    //    removeFriendChannel.trySend(position)
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


}