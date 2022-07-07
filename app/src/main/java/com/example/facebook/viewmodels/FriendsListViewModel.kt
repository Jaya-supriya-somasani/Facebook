package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class FriendsListViewModel : BaseViewModel() {
    private val friendsListChannel = Channel<Unit>(Channel.BUFFERED)
    val friendsListEvent = friendsListChannel.receiveAsFlow()
}