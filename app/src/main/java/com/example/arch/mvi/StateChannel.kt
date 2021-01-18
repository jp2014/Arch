package com.example.arch.mvi

import kotlinx.coroutines.CoroutineScope

interface StateChannel {
    fun initIntentHandling(viewModelScope: CoroutineScope)
}
