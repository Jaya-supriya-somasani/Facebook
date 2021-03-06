package com.example.facebook.adapter

import android.view.ViewGroup
import com.example.facebook.util.BaseAdapter
import com.example.facebook.util.BaseHolder
import com.example.facebook.R
import com.example.facebook.api.request.FriendDetailResponse
import com.example.facebook.api.request.FriendsListResponse
import com.example.facebook.api.request.SuggestFriendResponse
import com.example.facebook.util.BaseViewHolder
import com.example.facebook.util.inflate
import com.example.facebook.databinding.ItemFriendsListBinding

class SuggestFriendsAdapter(
    private val onAddFriendButtonClicked: (SuggestFriendResponse) -> Unit,
    private val onRemoveButtonClicked: (SuggestFriendResponse) -> Unit
) : BaseAdapter<SuggestFriendResponse>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<SuggestFriendResponse> =
        FriendsListViewHolder(parent.inflate(R.layout.item_friends_list))

    private inner class FriendsListViewHolder(binding: ItemFriendsListBinding) :
        BaseViewHolder<ItemFriendsListBinding, SuggestFriendResponse>(binding) {
        init {
            binding.btnAddFriend.setOnClickListener {
                onAddFriendButtonClicked(getItem(layoutPosition))
            }
            binding.btnRemove.setOnClickListener {
                onRemoveButtonClicked(getItem(layoutPosition))
            }
        }

        override fun onBind(item: SuggestFriendResponse) {
            binding.item = item
        }

    }
}