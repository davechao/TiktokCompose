package com.example.myapplication.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.ui.reels.ReelsScreen
import com.example.myapplication.navigation.DestinationRoute

fun NavGraphBuilder.reelsNavGraph(navController: NavController) {
    composable(
        route = DestinationRoute.REELS_SCREEN_ROUTE
    ) {
        ReelsScreen(navController)
    }
}