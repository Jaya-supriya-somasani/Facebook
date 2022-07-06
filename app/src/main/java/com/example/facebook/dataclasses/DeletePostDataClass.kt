package com.example.facebook.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DeletePostDataClass(
    @SerializedName("postid")
    val postId:String
):Parcelable