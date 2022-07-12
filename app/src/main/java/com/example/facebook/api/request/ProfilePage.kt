package com.example.facebook.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfilePage(
    @SerializedName("userId")
    val userId: Int = 0
) : Parcelable
