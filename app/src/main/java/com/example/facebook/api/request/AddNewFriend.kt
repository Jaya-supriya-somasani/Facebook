package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

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
) : Parcelable


@Parcelize
data class SuggestFriendResponse(
    @SerializedName("friendId")
    val friendId: String,
    @SerializedName("friendName")
    val friendName: String
) : Parcelable



