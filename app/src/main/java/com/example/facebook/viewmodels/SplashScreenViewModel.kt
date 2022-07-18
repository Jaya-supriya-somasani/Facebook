package com.example.facebook.viewmodels

import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : BaseViewModel() {

    private val navigateToNextScreenChannel = Channel<Boolean>()
    val navigateToNextScreenEvent = navigateToNextScreenChannel.receiveAsFlow()

    fun moveToNextScreen(isLoginScreen: Boolean, isLaunchImmediate: Boolean = false) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!isLaunchImmediate)
                delay(1000)

            navigateToNextScreenChannel.trySend(isLoginScreen)
        }
    }


}