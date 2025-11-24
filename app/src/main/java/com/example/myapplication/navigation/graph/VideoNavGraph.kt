package com.example.myapplication.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.ui.video.VideoScreen
import com.example.myapplication.navigation.DestinationRoute

fun NavGraphBuilder.videoNavGraph(navController: NavController) {
    composable(route = DestinationRoute.VIDEO_SCREEN_ROUTE) {
        VideoScreen(navController)
    }
}