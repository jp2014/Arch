package com.example.arch.model

import com.example.arch.repo.MainRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import javax.inject.Inject

data class MainState(
    val loading: Boolean = false,
    val title: String = "Yo",
    val buttonText: String = "Do Something"
)

sealed class MainIntent {
    object ChangeTitle : MainIntent()
    class DoSomething(val text: String) : MainIntent()
}

class MainStateChannel @Inject constructor(val mainRepo: MainRepo) {
    val userIntentChannel = Channel<MainIntent>()
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState>
        get() = _state

    suspend fun handleIntents() {
        userIntentChannel.consumeAsFlow().collect {
            reduce(it)
        }
    }

    private suspend fun reduce(intent: MainIntent) {
        return when (intent) {
            is MainIntent.ChangeTitle -> {
                _state.value = _state.value.copy(title = "Fake Event")
            }
            is MainIntent.DoSomething -> {
                _state.value = _state.value.copy(loading = true)
                mainRepo.getSomething().collect {
                    _state.value = _state.value.copy(loading = false, title = it)
                }
            }
        }
    }
}