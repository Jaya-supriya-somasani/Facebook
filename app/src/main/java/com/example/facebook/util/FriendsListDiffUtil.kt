package com.example.facebook.util

import androidx.recyclerview.widget.DiffUtil
import com.example.facebook.api.request.FriendDetailResponse

class FriendsListDiffUtil(
    private val oldFriendsListRes:List<FriendDetailResponse>,
    private val newFriendsListRes:List<FriendDetailResponse>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldFriendsListRes.size
    }

    override fun getNewListSize(): Int {
        return newFriendsListRes.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFriendsListRes[oldItemPosition].friendId==newFriendsListRes[newItemPosition].friendId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFriendsListRes[oldItemPosition].userName==newFriendsListRes[newItemPosition].userName && oldFriendsListRes[oldItemPosition].friendId==newFriendsListRes[newItemPosition].friendId

    }

}