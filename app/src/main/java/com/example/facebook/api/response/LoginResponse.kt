package com.example.facebook.api.response


class LoginStatus(
    var userId: String="",
    var userName: String="",
    var loginStatus: Boolean = false,
)

//class BaseResponse<T> {
//    @SerializedName("status")
//    var status: String = ""
//    @SerializedName("message")
//    var message: String = ""
//    @SerializedName("data")
//    var data: T? = null
//    @SerializedName("errorCode")
//    var errorCode: Int? = null
//}
