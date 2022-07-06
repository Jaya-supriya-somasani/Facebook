package com.example.facebook.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
    val loginStatus: String,
) : Parcelable {

}