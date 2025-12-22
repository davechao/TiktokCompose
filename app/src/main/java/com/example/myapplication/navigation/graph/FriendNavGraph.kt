package com.example.myapplication.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.myapplication.ui.friend.FriendScreen
import com.example.myapplication.navigation.DestinationRoute
import com.example.myapplication.navigation.DestinationRoute.ACCOUNT_ID
import com.example.myapplication.navigation.RootGraph
import com.example.myapplication.ui.firenddetail.FriendDetailScreen

fun NavGraphBuilder.friendNavGraph() {
    navigation(
        route = RootGraph.FRIEND_GRAPH,
        startDestination = DestinationRoute.FRIEND_SCREEN_ROUTE
    ) {
        composable(
            route = DestinationRoute.FRIEND_SCREEN_ROUTE
        ) {
            FriendScreen()
        }

        composable(
            route = DestinationRoute.FRIEND_DETAIL_SCREEN_ROUTE,
            arguments = listOf(
                navArgument(ACCOUNT_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getInt(ACCOUNT_ID) ?: return@composable
            FriendDetailScreen(id = accountId)
        }
    }
}