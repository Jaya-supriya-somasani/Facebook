package com.example.facebook.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChangePasswordDataClass(
    @SerializedName("newPassword")
    val newPswd:String
):Parcelable
