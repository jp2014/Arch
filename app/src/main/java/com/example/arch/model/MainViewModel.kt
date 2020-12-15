package com.example.arch.model

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val stateChannel: MainStateChannel
) :
    ViewModel() {

    val userIntentChannel = stateChannel.userIntentChannel
    val state = stateChannel.state

    init {
        viewModelScope.launch {
            stateChannel.handleIntents()
        }
    }
}