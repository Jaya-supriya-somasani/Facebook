package com.example.facebook.adapter

import android.view.ViewGroup
import com.example.facebook.R
import com.example.facebook.api.request.FriendDetailResponse
import com.example.facebook.base.BaseHolder
import com.example.facebook.base.BaseListAdapter
import com.example.facebook.base.BaseViewHolder
import com.example.facebook.databinding.ItemFriendsBinding
import com.example.facebook.util.inflate

class FriendsListAdapter(
    private val onDeleteFriend:(FriendDetailResponse, Int) -> Unit,
) : BaseListAdapter<FriendDetailResponse>(FriendDetailResponse.DiffUtils()) {

//    private var olFriendsCount = ArrayList<FriendDetailResponse>()
//    var oldFriendsTotalCount=olFriendsCount.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<FriendDetailResponse> =
        AddFriendsListViewHolder(parent.inflate(R.layout.item_friends))

    private inner class AddFriendsListViewHolder(binding: ItemFriendsBinding) :
        BaseViewHolder<ItemFriendsBinding, FriendDetailResponse>(binding) {

        override fun onBind(item: FriendDetailResponse) {
            binding.item = item
            binding.removeFriendBtn.setOnClickListener { onDeleteFriend(getItem(adapterPosition),adapterPosition) }
        }
    }

//    fun setData(newFriendsCount: List<FriendDetailResponse>) {
//        val diffUtil = FriendsListDiffUtil(olFriendsCount, newFriendsCount)
//        val diffResult = DiffUtil.calculateDiff(diffUtil)
//        olFriendsCount.clear()
//        olFriendsCount.addAll(newFriendsCount)
//        diffResult.dispatchUpdatesTo(this)
//    }

}