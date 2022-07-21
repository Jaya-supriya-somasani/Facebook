package com.example.facebook.adapter

import android.view.ViewGroup
import com.example.facebook.R
import com.example.facebook.api.request.SuggestFriendResponse
import com.example.facebook.base.BaseHolder
import com.example.facebook.base.BaseListAdapter
import com.example.facebook.base.BaseViewHolder
import com.example.facebook.databinding.ItemFriendsListBinding
import com.example.facebook.util.inflate

class SuggestFriendsAdapter(
    private val onAddFriendButtonClicked: (SuggestFriendResponse,Int) -> Unit,
    private val onRemoveButtonClicked: (SuggestFriendResponse) -> Unit
) : BaseListAdapter<SuggestFriendResponse>(SuggestFriendResponse.DiffUtils()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<SuggestFriendResponse> =
        FriendsListViewHolder(parent.inflate(R.layout.item_friends_list))

    private inner class FriendsListViewHolder(binding: ItemFriendsListBinding) :
        BaseViewHolder<ItemFriendsListBinding, SuggestFriendResponse>(binding) {
        init {
            binding.btnAddFriend.setOnClickListener {
                onAddFriendButtonClicked(getItem(layoutPosition),layoutPosition)
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