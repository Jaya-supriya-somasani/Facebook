package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DeletePostDataClass(
    @SerializedName("postid")
    val postId:String
):Parcelable