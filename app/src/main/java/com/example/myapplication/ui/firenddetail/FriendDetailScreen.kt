package com.example.myapplication.ui.firenddetail

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.viewmodel.FriendDetailViewModel

@Composable
fun FriendDetailScreen(
    navController: NavController,
    viewModel: FriendDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val context: Context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchFriend(1)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        }

        if (uiState.value.isLoading) {
            CircularProgressIndicator()
        }
    }
}