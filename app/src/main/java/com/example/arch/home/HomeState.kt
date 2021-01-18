package com.example.arch.home

import com.example.arch.mvi.StateChannel
import com.example.arch.repo.MainRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val loading: Boolean = false,
    val title: String = "Yo",
    val buttonText: String = "Do Something"
)

sealed class HomeIntent {
    object ChangeTitle : HomeIntent()
    class DoSomething(val text: String) : HomeIntent()
}

class HomeStateChannel @Inject constructor(private val mainRepo: MainRepo) : StateChannel {
    val userIntentChannel = Channel<HomeIntent>()
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state

    override fun initIntentHandling(viewModelScope: CoroutineScope) {
        viewModelScope.launch {
            userIntentChannel.consumeAsFlow().collect {
                reduce(it)
            }
        }
    }

    private suspend fun reduce(intent: HomeIntent) {
        return when (intent) {
            is HomeIntent.ChangeTitle -> {
                _state.value = _state.value.copy(title = "Fake Event")
            }
            is HomeIntent.DoSomething -> {
                _state.value = _state.value.copy(loading = true)
                mainRepo.getSomething().collect {
                    _state.value = _state.value.copy(loading = false, title = it)
                }
            }
        }
    }
}