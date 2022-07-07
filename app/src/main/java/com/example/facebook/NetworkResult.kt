package com.example.facebook

import android.util.Log
import retrofit2.HttpException

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(val data: T) : NetworkResult<T>()
    class Error<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<T : Any>(val e: Throwable) : NetworkResult<T>()
}

suspend fun <T : Any> safeApi(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        val data: T = apiCall.invoke()
        NetworkResult.Success<T>(data = data)
    } catch (httpException: HttpException) {
//        if (httpException.code() == 401) { //AuthenticationException
//
//        }
//        if (httpException.code() == 408) { // SocketTimeoutException
//
//        }
        httpException.printStackTrace()
        Log.d(
            "TAG",
            "safeApi: statusCode: ${httpException.code()} Message: ${httpException.message}"
        )
        NetworkResult.Error<T>(httpException.code(), httpException.message)
    } catch (exception: Exception) {
        exception.printStackTrace()
        NetworkResult.Exception<T>(exception)
    }
}



