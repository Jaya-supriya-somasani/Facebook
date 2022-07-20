package com.example.facebook.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.asSharedFlow(scope: CoroutineScope, defaultValue: T): SharedFlow<T> {
    return this.stateIn(scope, SharingStarted.Eagerly, defaultValue)
}
