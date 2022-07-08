package com.example.facebook.api.response

import com.google.gson.annotations.SerializedName

//{
//    "status": "Success",
//    "message": "Logged in successfully!",
//    "data": {
//    "mail": "krupa@gmail.com",
//    "loginStatus": true
//},
//    "errorCode": null
//}


//{
//    "status": "Client Error",
//    "message": "Mail doesn't exist",
//    "data": "",
//    "errorCode": 404
//}


//{
//    "mail": "krupa@gmail.com",
//    "loginStatus": true
//}
class LoginStatus {
    @SerializedName("userId")
    var userId: Int = 0
    @SerializedName("loginStatus")
    var loginStatus: Boolean = false
}

class BaseResponse<T> {
    @SerializedName("status")
    var status: String = ""
    @SerializedName("message")
    var message: String = ""
    @SerializedName("data")
    var data: T? = null
    @SerializedName("errorCode")
    var errorCode: Int? = null
}
