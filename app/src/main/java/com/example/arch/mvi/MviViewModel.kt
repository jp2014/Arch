package com.example.arch.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

abstract class MviViewModel<T, F>(stateChannel: StateChannel) : ViewModel() {
    /**
     * Represents the full state of the view
     */
    abstract val state: StateFlow<T>

    /**
     * Channel to send user intents that modify the
     * and/or trigger side effects
     */
    abstract val userIntentChannel: Channel<F>

    init {
        stateChannel.initIntentHandling(viewModelScope)
    }
}