package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class RegisterUser(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("dateOfBirth")
    val userDob: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("userPassword")
    val userPassword: String,
    @SerializedName("confirmPassword")
    val confirmPswd: String,
    @SerializedName("loginStatus")
    val loginStatus: Boolean,
) : Parcelable {

}