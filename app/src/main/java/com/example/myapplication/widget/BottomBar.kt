package com.example.myapplication.widget

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
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

    NavigationBarItem(
        label = {
            destination.title?.let {
                Text(
                    text = stringResource(id = destination.title),
                    style = MaterialTheme.typography.labelSmall,
                    softWrap = false,
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = when {
                            isCurrentBottomItemSelected -> 1f
                            else -> 0.7f
                        }
                    )
                )
            }
        },
        icon = {
            Icon(
                imageVector = when {
                    isCurrentBottomItemSelected -> destination.filledIcon
                    else -> destination.unfilledIcon
                } ?: destination.unfilledIcon,
                contentDescription = null,
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.surface,
            selectedIconColor = MaterialTheme.colorScheme.secondary,
            selectedTextColor = MaterialTheme.colorScheme.secondary
        ),
        selected = isCurrentBottomItemSelected,
        onClick = {
            navController.navigate(destination.route) {
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}