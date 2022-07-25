package com.example.facebook.adapter

import android.view.ViewGroup
import com.example.facebook.R
import com.example.facebook.api.request.FriendDetailResponse
import com.example.facebook.base.BaseAdapter
import com.example.facebook.base.BaseHolder
import com.example.facebook.base.BaseViewHolder
import com.example.facebook.databinding.ItemFriendsBinding
import com.example.facebook.generated.callback.OnClickListener
import com.example.facebook.util.inflate

class AddFriendsListAdapter(
    private val onDeleteFriend: (FriendDetailResponse, Int,Int) -> Unit,
    private val onDeleteFriendClickListener: OnClickListener? = null
) : BaseAdapter<FriendDetailResponse>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<FriendDetailResponse> =
        AddFriendsListViewHolder(parent.inflate(R.layout.item_friends))

    private inner class AddFriendsListViewHolder(binding: ItemFriendsBinding) :
        BaseViewHolder<ItemFriendsBinding, FriendDetailResponse>(binding) {

        override fun onBind(item: FriendDetailResponse) {
            binding.item = item
            binding.removeFriendBtn.setOnClickListener {
                onDeleteFriend(
                    getItem(layoutPosition),
                    layoutPosition,
                    itemCount-1
                )

                onDeleteFriendClickListener?.onClick(position)
                removeProduct(getItem(layoutPosition))
            }
        }

    }

    fun setUpdatedData(items: ArrayList<FriendDetailResponse>) {
        listItems.clear()
        listItems.addAll(items)
        notifyDataSetChanged()
    }


    fun removeProduct(model: FriendDetailResponse) {
        val position = listItems.indexOf(model)
        listItems.remove(model)
        notifyItemRemoved(position)
    }

    companion object {
        interface OnClickListener {
            fun onClick(position: Int) {

            }
        }
    }

}

