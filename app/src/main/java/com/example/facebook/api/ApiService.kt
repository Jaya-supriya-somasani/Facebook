package com.example.facebook.api

import com.example.facebook.api.request.LoginDataClass
import com.example.facebook.api.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/v1/login")
    suspend fun performLogin(@Body loginRequest: LoginDataClass): LoginResponse
}