package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class FriendsListViewModel : BaseViewModel() {
    private val friendsListStateFlow = MutableStateFlow<List<AllFriendsListResponse>>(emptyList())
    val friendsList: MutableStateFlow<List<AllFriendsListResponse>> = friendsListStateFlow
    private val toastEventChannel = Channel<String>()
    val toastEvent = toastEventChannel.receiveAsFlow()
    val totalFriendsList = MutableStateFlow(0)


    fun getFriendsList(userId: String) {
        viewModelScope.launch {
            when (val result = safeApi { NetworkService.apiService.getFriendsList(userId) }) {
                is NetworkResult.Success -> {
                    friendsListStateFlow.value = result.data.body()?.data!!
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


}