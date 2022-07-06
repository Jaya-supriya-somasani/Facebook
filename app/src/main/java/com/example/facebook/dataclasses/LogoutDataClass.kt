package com.example.facebook.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LogoutDataClass(
    @SerializedName("userId")
    val userId:String
):Parcelable
