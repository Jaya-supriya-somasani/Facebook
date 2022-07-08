package com.example.facebook.api.request

import android.os.Parcelable
import com.example.facebook.api.response.PostsResponsesItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetPosts(
    @SerializedName("postData")
    val postData: List<PostsResponsesItem>,
) : Parcelable
