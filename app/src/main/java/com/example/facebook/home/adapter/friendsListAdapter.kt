package com.example.facebook.home.adapter

import android.view.ViewGroup
import com.example.facebook.base.BaseAdapter
import com.example.facebook.base.BaseHolder
import com.example.facebook.dataclasses.FriendsListResponse
import com.example.facebook.R
import com.example.facebook.base.BaseViewHolder
import com.example.facebook.base.inflate
import com.example.facebook.databinding.ItemFriendsListBinding

class friendsListAdapter(
    private val onAddFriendButtonClicked: (FriendsListResponse) -> Unit,
    private val onRemoveButtonClicked: (FriendsListResponse) -> Unit
) : BaseAdapter<FriendsListResponse>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<FriendsListResponse> =
        FriendsListViewHolder(parent.inflate(R.layout.item_friends_list))

    private inner class FriendsListViewHolder(binding: ItemFriendsListBinding) :
        BaseViewHolder<ItemFriendsListBinding, FriendsListResponse>(binding) {
        init {
            binding.btnAddFriend.setOnClickListener {
                onAddFriendButtonClicked(getItem(layoutPosition))
            }
            binding.btnRemove.setOnClickListener {
                onRemoveButtonClicked(getItem(layoutPosition))
            }
        }

        override fun onBind(item: FriendsListResponse) {
            binding.item = item
        }

    }
}