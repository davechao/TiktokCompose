package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myapplication.navigation.graph.inboxNavGraph
import com.example.myapplication.navigation.graph.profileNavGraph
import com.example.myapplication.navigation.graph.videoNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = DestinationRoute.VIDEO_PAGE_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        videoNavGraph(navController)
        inboxNavGraph(navController)
        profileNavGraph(navController)
    }
}