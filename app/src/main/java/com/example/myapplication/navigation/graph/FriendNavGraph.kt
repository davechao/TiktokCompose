package com.example.myapplication.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.myapplication.ui.friend.FriendScreen
import com.example.myapplication.navigation.DestinationRoute
import com.example.myapplication.navigation.RootGraph
import com.example.myapplication.ui.firenddetail.FriendDetailScreen

fun NavGraphBuilder.friendNavGraph() {
    navigation(
        route = RootGraph.Friend.graph,
        startDestination = DestinationRoute.Friend.route
    ) {
        composable(
            route = DestinationRoute.Friend.route
        ) {
            FriendScreen()
        }

        composable(
            route = DestinationRoute.FriendDetail.route,
            arguments = listOf(
                navArgument("accountId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getInt("accountId") ?: return@composable
            FriendDetailScreen(id = accountId)
        }
    }
}