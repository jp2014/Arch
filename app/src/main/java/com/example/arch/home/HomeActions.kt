package com.example.arch.home

import com.example.arch.mvi.MviViewModel
import javax.inject.Inject

sealed class HomeOperation {
    object SomeEvent: HomeOperation()
    class DoSomething(val text: String) : HomeOperation()
}

/**
 * Actions - expose available actions to the view. this will be transformed into intents that we'll
 * handle in the state channel.
 * Example - we might have a submit action that transforms into a submit intent for the the state channel
 * to handle.
 */
class MainActions @Inject constructor() {
    fun action(operation: HomeOperation, viewModel: MviViewModel<HomeState, HomeIntent>) =
        when (operation) {
            HomeOperation.SomeEvent -> viewModel.userIntentChannel.offer(HomeIntent.ChangeTitle)

            is HomeOperation.DoSomething -> viewModel.userIntentChannel.offer(
                HomeIntent.DoSomething(operation.text)
            )
        }
}