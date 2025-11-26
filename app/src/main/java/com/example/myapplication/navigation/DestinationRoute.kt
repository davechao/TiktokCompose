package com.example.myapplication.navigation


object DestinationRoute {
    const val ACCOUNT_ID = "accountId"

    const val REELS_SCREEN_ROUTE = "reels_screen_route"
    const val FRIEND_SCREEN_ROUTE = "friend_screen_route"
    const val FRIEND_DETAIL_SCREEN_ROUTE = "friend_detail_screen_route/{$ACCOUNT_ID}"

    fun friendDetailRoute(id: Int) = "friend_detail_screen_route/$id"
}