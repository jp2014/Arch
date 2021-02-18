package com.example.arch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import com.example.arch.home.MainUi
import com.example.arch.player.PlayerUi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainUi: MainUi

    @Inject
    lateinit var playerUi: PlayerUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Arch Demo") }) },
                    bodyContent = { mainUi.Home() }
                )
            }
        }
    }

    /**
     * Start using this when hilt viewmodel issue is fixed for navigation
     */
//    @Composable
//    fun Navigation() {
//        val navController = rememberNavController()
//        NavHost(navController, startDestination = "Home") {
//            composable("Home") { mainUi.Home() }
//            composable("Player") { playerUi.Player() }
//        }
//    }

}