package com.example.facebook.api.request

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddNewFriend(
    @SerializedName("friendId")
    val friendId: String,
    @SerializedName("userId")
    val userId: String
) : Parcelable

@Parcelize
data class FriendDetailResponse(
    @SerializedName("friendId")
    val friendId: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    var userName: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("gender")
    val gender: String,
) : Parcelable{
    class DiffUtils : DiffUtil.ItemCallback<FriendDetailResponse>() {
        override fun areItemsTheSame(oldItem: FriendDetailResponse, newItem: FriendDetailResponse) =
            oldItem.userName.equals(newItem.userName, true) &&
                    oldItem.userId == newItem.userId &&
                    oldItem.mail == newItem.mail


        override fun areContentsTheSame(oldItem: FriendDetailResponse, newItem: FriendDetailResponse) =
            oldItem.friendId == newItem.friendId
    }
}


@Parcelize
data class SuggestFriendResponse(
    @SerializedName("friendId")
    val friendId: String,
    @SerializedName("friendName")
    val friendName: String
) : Parcelable{
    class DiffUtils : DiffUtil.ItemCallback<SuggestFriendResponse>() {
        override fun areItemsTheSame(oldItem: SuggestFriendResponse, newItem: SuggestFriendResponse) =
            oldItem.friendName.equals(newItem.friendName, true)

        override fun areContentsTheSame(oldItem: SuggestFriendResponse, newItem: SuggestFriendResponse) =
            oldItem.friendId == newItem.friendId
    }
}



