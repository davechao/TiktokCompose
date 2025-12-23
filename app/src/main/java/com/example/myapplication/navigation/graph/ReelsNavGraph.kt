package com.example.myapplication.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.ui.reels.ReelsScreen
import com.example.myapplication.navigation.DestinationRoute
import com.example.myapplication.navigation.RootGraph

fun NavGraphBuilder.reelsNavGraph() {
    navigation(
        route = RootGraph.Reels.graph,
        startDestination = DestinationRoute.Reels.route
    ) {
        composable(
            route = DestinationRoute.Reels.route
        ) {
            ReelsScreen()
        }
    }
}