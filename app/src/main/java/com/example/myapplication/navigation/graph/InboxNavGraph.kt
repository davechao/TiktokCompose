package com.example.myapplication.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.page.InboxPage
import com.example.myapplication.navigation.DestinationRoute

fun NavGraphBuilder.inboxNavGraph(navController: NavController) {
    composable(route = DestinationRoute.INBOX_PAGE_ROUTE) {
        InboxPage(navController)
    }
}