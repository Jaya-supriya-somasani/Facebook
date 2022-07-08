package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetUserProfile(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("dateOfBirth")
    val userDob: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("loginStatus")
    val loginStatus: Boolean,
) : Parcelable
