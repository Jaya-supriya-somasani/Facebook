package com.example.facebook.util

import androidx.recyclerview.widget.DiffUtil
import com.example.facebook.api.response.PostsResponsesItem

class DiffUtilKtx(
    private val prevPostData: List<PostsResponsesItem>,
    private val newPostData: List<PostsResponsesItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return prevPostData.size
    }

    override fun getNewListSize(): Int {
        return newPostData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return prevPostData[oldItemPosition].likeStatus == newPostData[newItemPosition].likeStatus
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (prevPostData[oldItemPosition].likesCount == newPostData[newItemPosition].likesCount) &&
                (prevPostData[oldItemPosition].likeStatus == newPostData[newItemPosition].likeStatus)&&
                (prevPostData[oldItemPosition].isCreated == newPostData[newItemPosition].isCreated)
    }

}