package com.example.myapplication.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.myapplication.page.ProfilePage
import com.example.myapplication.navigation.DestinationRoute

fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    composable(route = DestinationRoute.PROFILE_PAGE_ROUTE) {
        ProfilePage(navController)
    }
}