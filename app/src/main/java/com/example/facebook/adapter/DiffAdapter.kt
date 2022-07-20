package com.example.facebook.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.api.response.PostsResponsesItem
import com.example.facebook.databinding.ItemFacebookPostsBinding
import com.example.facebook.util.DiffUtilKtx

class DiffAdapter(
    private val onDeletePostClicked: (PostsResponsesItem, Int) -> Unit,
    private val onPostLiked: (PostsResponsesItem, Int) -> Unit
) : RecyclerView.Adapter<DiffAdapter.DiffViewHolder>() {

    private var postsResponseOld = ArrayList<PostsResponsesItem>()

    inner class DiffViewHolder(val binding: ItemFacebookPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.ivDelete.setOnClickListener {
                onDeletePostClicked(getItem(layoutPosition), layoutPosition)
            }

            binding.ivLike.setOnClickListener {
                onPostLiked(getItem(layoutPosition), layoutPosition)
            }
        }
    }

    private fun getItem(position: Int): PostsResponsesItem {
        return postsResponseOld[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffViewHolder {
        return DiffViewHolder(
            ItemFacebookPostsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: DiffViewHolder, position: Int) {
//        holder.binding.tvPostDescription.text = postsResponseOld[position].postData
//        holder.binding.tvLikeCount.text = postsResponseOld[position].likesCount
//        holder.binding.tvUserName.text = postsResponseOld[position].userName
        val currentPost = postsResponseOld[position]
        holder.binding.item = currentPost
        Log.d("Adapter", "onBindViewHolder: $currentPost")
    }

    override fun getItemCount(): Int {
        return postsResponseOld.size
    }

    fun setData(updatedPostsDetails: List<PostsResponsesItem>) {
        val diffUtil = DiffUtilKtx(postsResponseOld, updatedPostsDetails)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        postsResponseOld.clear()
        postsResponseOld.addAll(updatedPostsDetails)
        diffResult.dispatchUpdatesTo(this)

//        postsResponseOld.add
//        notifyDataSetChanged()
    }
}
