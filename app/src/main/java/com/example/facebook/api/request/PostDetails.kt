package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDetails(
    @SerializedName("postId")
    val postId:String
):Parcelable