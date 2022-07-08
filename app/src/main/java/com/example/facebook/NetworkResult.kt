package com.example.facebook

import android.util.Log
import com.example.facebook.api.response.BaseResponse
import retrofit2.HttpException

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(val data: T) : NetworkResult<T>()
    class Failure<T : Any>(val message: String?) : NetworkResult<T>()
    class Exception<T : Any>(val message: String?) : NetworkResult<T>()
}

suspend fun <T : Any> safeApi(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        val data: T = apiCall.invoke()
        if (data is BaseResponse<*>) {
            if (data.status.equals("success", true)) {
                NetworkResult.Success<T>(data = data)
            } else {
                NetworkResult.Failure<T>(message = data.message)
            }
        } else
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
        NetworkResult.Exception<T>("${httpException.code()} -> ${httpException.message}")
    } catch (exception: Exception) {
        exception.printStackTrace()
        NetworkResult.Exception<T>(exception.message)
    }
}



