package com.example.myapplication.navigation

sealed class DestinationRoute(val route: String) {

    data object Reels : DestinationRoute("reels_screen_route")
    data object Friend : DestinationRoute("friend_screen_route")
    data object FriendDetail : DestinationRoute("friend_detail_screen_route/{accountId}") {
        fun createRoute(accountId: Int) = "friend_detail_screen_route/$accountId"
    }
}
