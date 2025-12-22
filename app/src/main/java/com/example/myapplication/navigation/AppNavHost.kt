package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myapplication.navigation.graph.friendNavGraph
import com.example.myapplication.navigation.graph.reelsNavGraph

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController not provided")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = RootGraph.REELS_GRAPH
) {
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            reelsNavGraph()
            friendNavGraph()
        }
    }
}