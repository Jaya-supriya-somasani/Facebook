package com.example.facebook.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.facebook.R
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.databinding.ItemFacebookPostsBinding
import com.example.facebook.util.BaseAdapter
import com.example.facebook.util.BaseHolder
import com.example.facebook.util.BaseViewHolder
import com.example.facebook.util.inflate

class PostAdapter(
    private val onDeletePostClicked: (PostsResponsesItem) -> Unit,
    private val onPostLiked: (PostsResponsesItem) -> Unit,
) : BaseAdapter<PostsResponsesItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<PostsResponsesItem> = PostViewHolder(parent.inflate(R.layout.item_facebook_posts))

    private inner class PostViewHolder(binding: ItemFacebookPostsBinding) :
        BaseViewHolder<ItemFacebookPostsBinding, PostsResponsesItem>(binding) {


        init {
            binding.ivDelete.setOnClickListener {
                onDeletePostClicked(getItem(layoutPosition))
            }

            binding.ivLike.setOnClickListener {
                onPostLiked(getItem(layoutPosition))
            }
        }

        override fun onBind(item: PostsResponsesItem) {
            binding.item = item
            if (item.likesCount == "0") {
                binding.tvLikeCount.visibility = View.GONE
            } else {
                binding.tvLikeCount.visibility = View.VISIBLE
                val likesCountText = binding.root.context.resources.getQuantityString(
                    R.plurals.likes_count, item.likesCount.toInt(), item.likesCount.toInt()
                )
                binding.tvLikeCount.text = likesCountText
            }
        }

    }
}