package com.example.facebook.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DisplayFriendsResponse(
    @SerializedName("userId")
    val userId:Int=0,
    @SerializedName("userName")
    val userName:String="",
    @SerializedName("mail")
    val mail:String="",
    @SerializedName("dateOfBirth")
    val dateOfBirth:String="",
    @SerializedName("gender")
    val gender:String="",
    @SerializedName("friendId")
    val friendId:Int=0
):Parcelable