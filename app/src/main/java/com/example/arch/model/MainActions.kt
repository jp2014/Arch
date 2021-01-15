package com.example.arch.model

import javax.inject.Inject

sealed class MainOperation {
    object SomeEvent: MainOperation()
    class DoSomething(val text: String) : MainOperation()
}

class MainActions @Inject constructor() {
    fun action(operation: MainOperation, viewModel: MainViewModel) =
        when (operation) {
            MainOperation.SomeEvent -> viewModel.userIntentChannel.offer(MainIntent.ChangeTitle)

            is MainOperation.DoSomething -> viewModel.userIntentChannel.offer(
                MainIntent.DoSomething(operation.text)
            )
        }
}