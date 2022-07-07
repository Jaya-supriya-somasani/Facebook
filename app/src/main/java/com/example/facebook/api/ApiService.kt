package com.example.facebook.api

import com.example.facebook.api.request.LoginDataClass
import com.example.facebook.api.request.RegisterUser
import com.example.facebook.api.response.LoginResponse
import com.example.facebook.api.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/v1/login")
    suspend fun performLogin(@Body loginRequest: LoginDataClass): LoginResponse


    @POST("/api/v1/register")
    suspend fun registerUser(@Body registerRequest:RegisterUser):RegisterResponse
}