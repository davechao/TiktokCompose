package com.example.myapplication.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.myapplication.navigation.BottomBarDestination

@Composable
fun BottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    NavigationBar {
        BottomBarDestination.entries.forEach {
            BottomItem(
                destination = it,
                navController = navController,
                currentDestination = currentDestination,
            )
        }
    }
}

@Composable
private fun RowScope.BottomItem(
    destination: BottomBarDestination,
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    val isCurrentBottomItemSelected = currentDestination?.hierarchy?.any {
        it.route == destination.route
    } ?: false

    var icon: Int = destination.unFilledIcon
    destination.apply {
        if (isCurrentBottomItemSelected) {
            filledIcon?.let { icon = it }
        }
    }

    NavigationBarItem(
        label = {
            destination.title?.let {
                Text(
                    text = stringResource(id = destination.title),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.surface,
            selectedIconColor = MaterialTheme.colorScheme.secondary,
            selectedTextColor = MaterialTheme.colorScheme.secondary
        ),
        selected = isCurrentBottomItemSelected,
        onClick = {
            destination.route.let {
                navController.navigate(it) {
                    launchSingleTop = true
                }
            }
        }
    )
}