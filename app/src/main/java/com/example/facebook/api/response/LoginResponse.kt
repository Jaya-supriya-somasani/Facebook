package com.example.facebook.api.response

import com.google.gson.annotations.SerializedName


class LoginResponse {
    @SerializedName("userId")
    var userId: Int = 0
    @SerializedName("loginStatus")
    var loginStatus: Boolean = false
}
