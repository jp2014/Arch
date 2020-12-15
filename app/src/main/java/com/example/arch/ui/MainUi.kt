package com.example.arch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.example.arch.model.MainActions
import com.example.arch.model.MainOperation
import com.example.arch.model.MainState
import com.example.arch.model.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class MainUi @Inject constructor(private val mainActions: MainActions) {

    @Composable
    fun Top() {
        val viewModel = viewModel<MainViewModel>()
        val state = viewModel.state.collectAsState()
        val doSomething =
            mainActions.action(
                MainOperation.DoSomething("something was done"),
                viewModel
            )

        Main(mainState = state.value, doSomething = { doSomething })
    }

    @Composable
    fun Main(mainState: MainState, doSomething: () -> Unit) {
        val topPadding = Modifier.padding(top = 8.dp)
        Column(modifier = Modifier.padding(16.dp)) {
            Text(mainState.title, modifier = topPadding)
            Button(
                modifier = topPadding,
                onClick =  doSomething
            ) {
                Text("Do something")
            }
            if (mainState.loading)
                CircularProgressIndicator(modifier = topPadding)
        }
    }
}

@Preview
@Composable
private fun MainUiPreview() {
    val mainUi = MainUi(MainActions())
    Column {
        mainUi.Main(
            mainState = MainState()
        ) {}
    }
}


