package com.example.myapplication.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.ui.inbox.InboxScreen
import com.example.myapplication.navigation.DestinationRoute

fun NavGraphBuilder.inboxNavGraph(navController: NavController) {
    composable(route = DestinationRoute.INBOX_SCREEN_ROUTE) {
        InboxScreen(navController)
    }
}