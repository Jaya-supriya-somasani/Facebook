package com.example.facebook.home.adapter

import android.view.ViewGroup
import com.example.facebook.R
import com.example.facebook.base.BaseAdapter
import com.example.facebook.base.BaseHolder
import com.example.facebook.base.BaseViewHolder
import com.example.facebook.base.inflate
import com.example.facebook.databinding.ItemFacebookPostsBinding
import com.example.facebook.dataclasses.PostsResponsesItem

class PostAdapter : BaseAdapter<PostsResponsesItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<PostsResponsesItem> = PostViewHolder(parent.inflate(R.layout.item_facebook_posts))

    private inner class PostViewHolder(binding: ItemFacebookPostsBinding) :
        BaseViewHolder<ItemFacebookPostsBinding, PostsResponsesItem>(binding) {

        override fun onBind(item: PostsResponsesItem) {
            binding.tvUserName.text = item.userId
            binding.tvPostDescription.text = item.postDesc
            binding.ivLike.text = "23"
            binding.tvLikeCount.text = "2k"
            binding.tvCommentsCount.text = "45 comments"
            binding.tvShareCount.text = "12 shares"
            binding.ivLike.isChecked = item.likeStatus
        }

    }
}