package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatePost(
    @SerializedName("userId")
    val userId:String,
    @SerializedName("postData")
    val postData:String
):Parcelable
