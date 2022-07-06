package com.example.facebook.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class FriendsListResponse(
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("user_description")
    val userDesc: String,
    @SerializedName("user_image")
    val userImage: String,
) : Parcelable {

}
