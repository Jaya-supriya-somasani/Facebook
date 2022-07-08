package com.example.facebook.api.response

import com.google.gson.annotations.SerializedName

class RegisterResponse {

    @SerializedName("status")
    val status:String=""
    @SerializedName("message")
    val message:String=""
    @SerializedName("data")
    val data:RegisterMail?=null
    @SerializedName("errorCode")
    val errorCode:Number=0
}

class RegisterMail{
    @SerializedName("mail")
    val mail:String=""

}