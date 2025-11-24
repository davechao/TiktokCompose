package com.example.myapplication.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.ui.friend.FriendScreen
import com.example.myapplication.navigation.DestinationRoute

fun NavGraphBuilder.friendNavGraph(navController: NavController) {
    composable(route = DestinationRoute.FRIEND_SCREEN_ROUTE) {
        FriendScreen(navController)
    }
}