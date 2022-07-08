package com.example.facebook.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostsResponsesItem(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("post_id")
    val postId: String,
    @SerializedName("post_description")
    val postDesc: String,

    ) : Parcelable {
    @IgnoredOnParcel
    val likeStatus: Boolean = false
}


@Parcelize
data class LoginRequest(
    @SerializedName("mail")
    val userEmail: String,
    @SerializedName("userPassword")
    val userPassword: String,
) : Parcelable

class BaseResponse<T>(
    @SerializedName("status")
    val status: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T?,
    @SerializedName("errorCode")
    var errorCode: String? = null
)

@Parcelize
data class RegisterRequest(
    @SerializedName("status")
    val confirmPassword: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("loginStatus")
    val loginStatus: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userPassword")
    val userPassword: String
) : Parcelable


@Parcelize
data class ChangePasswordRequest(
    @SerializedName("newPassword")
    val newPassword: String,
    @SerializedName("confirmPassword")
    val confirmPassword: String,
) : Parcelable