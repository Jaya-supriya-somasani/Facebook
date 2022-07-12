package com.example.facebook.adapter

import android.view.ViewGroup
import com.example.facebook.util.BaseAdapter
import com.example.facebook.util.BaseHolder
import com.example.facebook.R
import com.example.facebook.api.request.AddNewFriend
import com.example.facebook.api.request.AllFriendsListResponse
import com.example.facebook.api.request.FriendsListResponse
import com.example.facebook.databinding.ItemFriendsBinding
import com.example.facebook.util.BaseViewHolder
import com.example.facebook.util.inflate
import com.example.facebook.databinding.ItemFriendsListBinding

class AddFriendsListAdapter(
    private val onRemoveButtonClicked: (AllFriendsListResponse) -> Unit
) : BaseAdapter<AllFriendsListResponse>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<AllFriendsListResponse> =
        AddFriendsListViewHolder(parent.inflate(R.layout.item_friends))

    private inner class AddFriendsListViewHolder(binding: ItemFriendsBinding) :
        BaseViewHolder<ItemFriendsBinding, AllFriendsListResponse>(binding) {
        init {
            binding.removeFrdBtn.setOnClickListener {
                onRemoveButtonClicked(getItem(layoutPosition))
            }
        }

        override fun onBind(item: AllFriendsListResponse) {
            binding.item = item
        }

    }
}