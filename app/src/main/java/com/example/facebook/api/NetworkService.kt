package com.example.facebook.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private val mainUrl="http://stagetao.gcf.education:3000"
    val retrofit = Retrofit.Builder()
        .baseUrl(mainUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService=retrofit.create(ApiService::class.java)
}