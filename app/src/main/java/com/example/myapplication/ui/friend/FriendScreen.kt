package com.example.myapplication.ui.friend

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.myapplication.model.FriendItem
import com.example.myapplication.navigation.DestinationRoute
import com.example.myapplication.navigation.LocalNavController
import com.example.myapplication.ui.event.UiEvent
import com.example.myapplication.viewmodel.FriendViewModel

@Composable
fun FriendScreen(
    viewModel: FriendViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val navController = LocalNavController.current
    val context = LocalContext.current

    val onItemClick = remember {
        { id: Int ->
            navController.navigate(
                route = DestinationRoute.friendDetailRoute(id)
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                items = uiState.value.friends,
                key = { it.id }
            ) { item ->
                FriendItemView(
                    item = item,
                    onClick = onItemClick,
                )

                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 0.5.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if (uiState.value.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun FriendItemView(
    item: FriendItem,
    onClick: (Int) -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(text = item.login ?: "")
        },
        modifier = Modifier.clickable {
            onClick(item.id)
        },
        leadingContent = {
            AsyncImage(
                model = item.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        BorderStroke(width = 1.dp, color = Color.Black),
                        shape = CircleShape,
                    )
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
        },
    )
}