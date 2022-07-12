package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeletePostDataClass(
    @SerializedName("postId")
    val postId: String,
    @SerializedName("friendId")
    val friendId: String,

    // add isDeleteEnabled Field
) : Parcelable