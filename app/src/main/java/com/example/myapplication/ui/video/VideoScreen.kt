package com.example.myapplication.ui.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.DarkPink
import com.example.myapplication.viewmodel.VideoViewModel

@Composable
fun VideoScreen(
    navController: NavController,
    viewModel: VideoViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

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
            onclickComment = { videoId -> },
            onClickLike = { videoId, likeStatus -> },
            onclickFavourite = {},
            onClickAudio = {},
            onClickUser = { userId -> }
        )
    }
}