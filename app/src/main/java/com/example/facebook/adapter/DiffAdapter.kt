package com.example.facebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.api.request.FriendDetailResponse
import com.example.facebook.databinding.ItemFriendsBinding

class DiffAdapter : RecyclerView.Adapter<DiffAdapter.DiffViewHolder>() {

    private val friendsDetails = emptyList<FriendDetailResponse>()

    class DiffViewHolder(val binding: ItemFriendsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffViewHolder {
        return DiffViewHolder(
            ItemFriendsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DiffViewHolder, position: Int) {
        holder.binding.usernameTv.text = friendsDetails[position].userName
    }

    override fun getItemCount(): Int {
        return friendsDetails.size
    }

    fun setData(newFriendsDetails:List<FriendDetailResponse>){
//        val
    }
}
