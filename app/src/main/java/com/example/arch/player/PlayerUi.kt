package com.example.arch.player

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import javax.inject.Inject

class PlayerUi @Inject constructor() {

    @Composable
    fun Player() {
        Text("This is a player screen!")
    }
}