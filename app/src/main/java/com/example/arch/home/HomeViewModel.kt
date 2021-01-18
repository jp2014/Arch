package com.example.arch.home

import androidx.hilt.lifecycle.ViewModelInject
import com.example.arch.mvi.MviViewModel

class HomeViewModel @ViewModelInject constructor(stateChannel: HomeStateChannel) :
    MviViewModel<HomeState, HomeIntent>(stateChannel) {

    override val userIntentChannel = stateChannel.userIntentChannel
    override val state = stateChannel.state
}