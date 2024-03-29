package com.example.facebook.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoveFriendRes(
    @SerializedName("friendId")
    val friendId: String
) : Parcelable