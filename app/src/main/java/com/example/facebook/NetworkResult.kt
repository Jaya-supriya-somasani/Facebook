package com.example.facebook

import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class NetworkResult<T : Any> {
    class Success<T: Any>(val data: T) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
}

suspend fun <T: Any> safeApi(apiCall:suspend()-> T): NetworkResult<T> {
    return try {
        val data: T = apiCall.invoke()
        NetworkResult.Success<T>(data = data)
    } catch (httpException: HttpException) {
        if(httpException.statusCode == 401) { //AuthenticationException

        }
        if(httpException.statusCode == 408) { // SocketTimeoutException

        }
        NetworkResult.Error<T>(httpException.statusCode, httpException.message)
    }
    catch (exception: Exception) {
        NetworkResult.Exception<T>(exception)
    }
}

suspend fun sampleApiCall() {
    val networkResult: NetworkResult<String> = safeApi { returnAPIResponse() }
}

fun returnAPIResponse() : String {
    return "Sample response"
}

fun apiImple() {
    CoroutineScope(Dispatchers.IO).launch {
        val networkResult: NetworkResult<String> = safeApi { returnAPIResponse() }
        when(networkResult) {
            is NetworkResult.Success -> {

            }
            is NetworkResult.Exception -> {
                val ex = networkResult.e.message
            }
            is NetworkResult.Error -> {
                val msg = networkResult.message
            }
        }
    }
}

