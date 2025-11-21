package com.example.myapplication.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.page.VideoPage
import com.example.myapplication.navigation.DestinationRoute

fun NavGraphBuilder.videoNavGraph(navController: NavController) {
    composable(route = DestinationRoute.VIDEO_PAGE_ROUTE) {
        VideoPage(navController)
    }
}