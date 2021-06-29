package com.example.arch.home

import com.example.arch.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(stateChannel: HomeStateChannel) :
    MviViewModel<HomeState, HomeIntent>(stateChannel) {

    override val userIntentChannel = stateChannel.userIntentChannel
    override val state = stateChannel.state
}