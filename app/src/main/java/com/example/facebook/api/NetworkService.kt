package com.example.facebook.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    companion object {
        private val mainUrl = "http://stagetao.gcf.education:3000"

        private val retrofit by lazy {
            val builder = Retrofit.Builder()
                .baseUrl(mainUrl)
                .addConverterFactory(GsonConverterFactory.create())

            val intercept = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(intercept)
                .build()
            builder.client(client)
            builder.build()
        }

        val apiService = retrofit.create(ApiService::class.java)
    }
}