package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChangePasswordRequest(
    @SerializedName("newPassword")
    val newPassword: String,
    @SerializedName("confirmPassword")
    val confirmPassword: String,
) : Parcelable
