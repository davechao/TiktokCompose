package com.example.myapplication.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.myapplication.R


enum class BottomBarDestination(
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val unFilledIcon: Int,
    @DrawableRes val filledIcon: Int? = null,
) {
    HOME(
        route = DestinationRoute.VIDEO_SCREEN_ROUTE,
        title = R.string.home,
        unFilledIcon = R.drawable.ic_home,
        filledIcon = R.drawable.ic_home_fill
    ),

    INBOX(
        route = DestinationRoute.INBOX_SCREEN_ROUTE,
        title = R.string.inbox,
        unFilledIcon = R.drawable.ic_inbox,
        filledIcon = R.drawable.ic_inbox_fill
    ),

    Profile(
        route = DestinationRoute.PROFILE_SCREEN_ROUTE,
        title = R.string.profile,
        unFilledIcon = R.drawable.ic_profile,
        filledIcon = R.drawable.ic_profile_fill
    ),
}