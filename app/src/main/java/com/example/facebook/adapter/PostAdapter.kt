package com.example.facebook.adapter

import android.view.ViewGroup
import com.example.facebook.R
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.util.BaseAdapter
import com.example.facebook.util.BaseHolder
import com.example.facebook.util.BaseViewHolder
import com.example.facebook.util.inflate
import com.example.facebook.databinding.ItemFacebookPostsBinding

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