package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myapplication.feature.inbox.inboxNavGraph
import com.example.myapplication.feature.profile.profileNavGraph
import com.example.myapplication.feature.video.videoNavGraph

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