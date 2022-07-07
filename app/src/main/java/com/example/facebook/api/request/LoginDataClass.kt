package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginDataClass(
    @SerializedName("mail")
    val username:String,
    @SerializedName("userPassword")
    val password:String
):Parcelable
