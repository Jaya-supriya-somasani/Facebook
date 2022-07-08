package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LikesCount(
    @SerializedName("postId")
    val postId:String,
    @SerializedName("count")
    val count:String,
):Parcelable
