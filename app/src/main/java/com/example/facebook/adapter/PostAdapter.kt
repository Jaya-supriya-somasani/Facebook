package com.example.facebook.adapter


import android.view.ViewGroup
import com.example.facebook.R
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.base.BaseHolder
import com.example.facebook.base.BaseListAdapter
import com.example.facebook.base.BaseViewHolder
import com.example.facebook.databinding.ItemFacebookPostsBinding
import com.example.facebook.util.inflate


class PostAdapter(
    private val onDeletePostClicked: (PostsResponsesItem) -> Unit,
    private val onPostLiked: (PostsResponsesItem) -> Unit,
) : BaseListAdapter<PostsResponsesItem>(PostsResponsesItem.DiffUtils()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<PostsResponsesItem> {
        return PostViewHolder(parent.inflate(R.layout.item_facebook_posts))
    }

    private inner class PostViewHolder(
        binding: ItemFacebookPostsBinding
    ) : BaseViewHolder<ItemFacebookPostsBinding, PostsResponsesItem>(binding) {
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
        }
    }

}