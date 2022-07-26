package com.example.facebook.internetconnection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
//    object UnDetermined : NetworkStatus()
}

class NetworkStatusHelper(val context: Context) : LiveData<NetworkStatus>() {

    val validNetworkConnections: ArrayList<Network> = ArrayList()
    var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        connectivityManagerCallback = getConnectivityManagerCallback()
        val networkRequest = NetworkRequest
            .Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, connectivityManagerCallback)
    }

    private fun getConnectivityManagerCallback() =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val networkCapability = connectivityManager.getNetworkCapabilities(network)
                val hasNetworkConnection =
                    networkCapability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        ?: false
                if (hasNetworkConnection) {
                    determineInternetAccess(network)
                }
                Log.d("TAG", "onAvailable: $hasNetworkConnection")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                validNetworkConnections.remove(network)
                announceStatus()
                Log.d("TAG","onConnectionLost")
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    determineInternetAccess(network)
                    //validNetworkConnections.add(network)
                    Log.d("TAG", "onCapabilitiesChanged: determineInternetAccess")
                } else {
                    validNetworkConnections.remove(network)
                    Log.d("TAG","Network connection removed")
                    announceStatus()
                }
            }
        }

    fun announceStatus() {
        if (validNetworkConnections.isNotEmpty()) {
            Log.d("TAG","PostValue: Available")
            postValue(NetworkStatus.Available)
        } else {
            Log.d("TAG","PostValue: Unavailable")
            postValue(NetworkStatus.Unavailable)
        }
    }


    private fun determineInternetAccess(network: Network) {
        CoroutineScope(Dispatchers.IO).launch {
            if (InternetAvailability.check()) {
                withContext(Dispatchers.Main) {
                    validNetworkConnections.add(network)
                    announceStatus()
                }
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    }
}

object InternetAvailability {

    fun check(): Boolean {
        return try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53))
            socket.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}


