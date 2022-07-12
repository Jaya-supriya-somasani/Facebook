package com.example.facebook.adapter

import android.view.ViewGroup
import com.example.facebook.R
import com.example.facebook.api.response.DisplayFriendsResponse
import com.example.facebook.base.BaseAdapter
import com.example.facebook.base.BaseViewHolder
import com.example.facebook.base.inflate
import com.example.facebook.databinding.ItemUserFriendsListBinding

class UserFriendsListAdapter() : BaseAdapter<DisplayFriendsResponse>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.example.facebook.base.BaseHolder<DisplayFriendsResponse> {
//        val view = parent.inflate(R.layout.item_user_friends_list)
//        val binding:ItemUserFriendsListBinding = ItemUserFriendsListBinding.bind(view)
        val b: ItemUserFriendsListBinding = parent.inflate(R.layout.item_user_friends_list, false)
        return Holder(b)
    }


    class Holder(binding: ItemUserFriendsListBinding) :
        BaseViewHolder<ItemUserFriendsListBinding, DisplayFriendsResponse>(binding) {

        override fun onBind(item: DisplayFriendsResponse) {
            binding.usernameTv.text=item.userName

        }

    }
}

