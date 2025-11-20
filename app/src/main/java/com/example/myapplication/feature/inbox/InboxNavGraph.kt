package com.example.myapplication.feature.inbox

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.navigation.DestinationRoute

fun NavGraphBuilder.inboxNavGraph(navController: NavController) {
    composable(route = DestinationRoute.INBOX_PAGE_ROUTE) {
        InboxPage(navController)
    }
}