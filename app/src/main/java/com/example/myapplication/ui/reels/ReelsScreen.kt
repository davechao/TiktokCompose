package com.example.myapplication.ui.reels

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.ui.event.UiEvent
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.DarkPink
import com.example.myapplication.viewmodel.ReelsViewModel

@Composable
fun ReelsScreen(
    viewModel: ReelsViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val context = LocalContext.current

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
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        DarkPink,
                        DarkBlue,
                    )
                )
            )
    ) {
        TikTokVerticalVideoPager(
            videos = uiState.value.videos,
        )
    }
}