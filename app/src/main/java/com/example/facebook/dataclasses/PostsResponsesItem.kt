package com.example.facebook.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class PostsResponsesItem(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("post_id")
    val postId: String,
    @SerializedName("post_description")
    val postDesc: String,

    ) : Parcelable {
    @IgnoredOnParcel
    val likeStatus: Boolean = false
}