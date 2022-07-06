package com.example.facebook.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginDataClass(
    @SerializedName("mail")
    val usermail:String,
    @SerializedName("userPassword")
    val password:String
):Parcelable
