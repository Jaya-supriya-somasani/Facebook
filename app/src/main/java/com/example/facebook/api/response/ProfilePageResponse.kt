package com.example.facebook.api.response

import com.google.gson.annotations.SerializedName

class ProfilePageResponse{
    @SerializedName("status")
    var status:String=""
    @SerializedName("message")
    var message:String=""
    @SerializedName("data")
    var data:userDetailsResponse?=null
    @SerializedName("errorCode")
    var errorCode:Int?=null
}

class userDetailsResponse{
    @SerializedName("userId")
    var userId:Int=0
    @SerializedName("userName")
    var userName:String=""
    @SerializedName("mail")
    var mail:String=""
    @SerializedName("dateOfBirth")
    var dateOfBirth:String=""
    @SerializedName("gender")
    var gender:String=""
    @SerializedName("loginStatus")
    var loginStatus:Boolean=false

}
