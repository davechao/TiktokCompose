package com.example.myapplication

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.widget.BottomBar
import com.example.myapplication.navigation.AppNavHost
import com.example.myapplication.navigation.DestinationRoute
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()

    val currentDestination = currentBackStackEntryAsState?.destination
    val context = LocalContext.current

    val isShowBottomBar = when (currentDestination?.route) {
        DestinationRoute.REELS_SCREEN_ROUTE,
        DestinationRoute.FRIEND_SCREEN_ROUTE -> true

        else -> false
    }

    val isDarkMode = when (currentDestination?.route) {
        DestinationRoute.REELS_SCREEN_ROUTE -> true
        else -> false
    }

    if (currentDestination?.route == DestinationRoute.REELS_SCREEN_ROUTE) {
        BackHandler {
            (context as? Activity)?.finish()
        }
    }

    MyApplicationTheme(
        darkTheme = isDarkMode
    ) {
        Scaffold(
            topBar = {

            },
            bottomBar = {
                if (isShowBottomBar.not()) {
                    return@Scaffold
                }
                BottomBar(
                    navController = navController,
                    currentDestination = currentDestination,
                )
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AppNavHost(navController = navController)
            }
        }
    }
}