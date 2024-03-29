package com.example.facebook.api

import android.content.Context
import com.example.facebook.datastore.AppDataStore
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkService {
    private val mainUrl = "http://stagetao.gcf.education:3000"

//    @Singleton
    @Provides
    fun providingRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mainUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

//    @Singleton
    @Provides
    fun providingApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

//    @Singleton
    @Provides
    fun loginIntercept(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

//    @Singleton
    @Provides
    fun providingHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideDataStore(context: Context): AppDataStore {
        return AppDataStore(context)
    }

}

// class NetworkService {
//
//    companion object {
//        private val mainUrl = "http://stagetao.gcf.education:3000"
//
//        private val retrofit by lazy {
//
//            val builder = Retrofit.Builder()
//                .baseUrl(mainUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//
//            val intercept = HttpLoggingInterceptor().apply {
//                this.level = HttpLoggingInterceptor.Level.BODY
//            }
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(intercept)
//                .build()
//            builder.client(client)
//            builder.build()
//        }
//
//        val apiService = retrofit.create(ApiService::class.java)
//    }
//}
//