package com.example.myapplication.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.R

enum class BottomBarDestination(
    val route: String,
    @StringRes val title: Int? = null,
    val unfilledIcon: ImageVector,
    val filledIcon: ImageVector? = null,
) {
    REELS(
        route = RootGraph.Reels.graph,
        title = R.string.reels,
        unfilledIcon =  Icons.Outlined.VideoLibrary,
        filledIcon = Icons.Filled.VideoLibrary,
    ),

    FRIEND(
        route = RootGraph.Friend.graph,
        title = R.string.friends,
        unfilledIcon = Icons.Outlined.People,
        filledIcon = Icons.Filled.People,
    ),
}