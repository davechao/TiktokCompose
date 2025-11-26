package com.example.myapplication.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.friend.FriendScreen
import com.example.myapplication.navigation.DestinationRoute
import com.example.myapplication.navigation.DestinationRoute.ACCOUNT_ID
import com.example.myapplication.ui.firenddetail.FriendDetailScreen

fun NavGraphBuilder.friendNavGraph(navController: NavController) {

    composable(
        route = DestinationRoute.FRIEND_SCREEN_ROUTE
    ) {
        FriendScreen(navController)
    }

    composable(
        route = DestinationRoute.FRIEND_DETAIL_SCREEN_ROUTE,
        arguments = listOf(
            navArgument(ACCOUNT_ID) { type = NavType.IntType },
        )
    ) { backStackEntry ->
        val accountId = backStackEntry.arguments?.getInt(ACCOUNT_ID) ?: return@composable
        FriendDetailScreen(
            navController = navController,
            id = accountId
        )
    }
}