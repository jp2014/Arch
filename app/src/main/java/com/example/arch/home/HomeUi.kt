package com.example.arch.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.example.arch.mvi.MviViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class MainUi @Inject constructor(private val mainActions: MainActions) {

    @Composable
    fun Home() {
        val viewModel: MviViewModel<HomeState, HomeIntent> = viewModel<HomeViewModel>()

        val state = viewModel.state.collectAsState()
        val doSomething: () -> Unit = {
            mainActions.action(
                HomeOperation.DoSomething("something was done"),
                viewModel
            )
        }

        Main(homeState = state.value, doSomething = doSomething)
    }

    @Composable
    fun Main(homeState: HomeState, doSomething: () -> Unit) {
        val topPadding = Modifier.padding(top = 8.dp)
        Column(modifier = Modifier.padding(16.dp)) {
            Text(homeState.title, modifier = topPadding)
            Button(
                modifier = topPadding,
                onClick = doSomething
            ) {
                Text("Do something")
            }
            if (homeState.loading)
                CircularProgressIndicator(modifier = topPadding)
        }
    }
}

/**
 * Preview is currently broken w/ the latest compose libs. I've tested this w/ a clean project.
 * There is a fix planned for late Jan 2021
 */
@Preview
@Composable
private fun MainUiPreview() {
    val mainUi = MainUi(MainActions())
    Column {
        mainUi.Main(
            homeState = HomeState()
        ) {}
    }
}


