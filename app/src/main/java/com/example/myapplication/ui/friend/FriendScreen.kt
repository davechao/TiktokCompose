package com.example.myapplication.ui.friend

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.viewmodel.FriendViewModel

@Composable
fun FriendScreen(
    navController: NavController,
    viewModel: FriendViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                items = uiState.value.friends
            ) { item ->
                ListItem(
                    headlineContent = {
                        Text(text = item.login ?: "")
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
                    supportingContent = {
                        Text(text = item.htmlUrl ?: "")
                    }
                )
            }
        }

        if (uiState.value.isLoading) {
            CircularProgressIndicator()
        }
    }
}