package com.example.myapplication.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.widget.TikTokVerticalVideoPager
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.DarkPink
import com.example.myapplication.viewmodel.VideoViewModel

@Composable
fun VideoPage(
    navController: NavController,
    viewModel: VideoViewModel = hiltViewModel()
) {
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
//        TikTokVerticalVideoPager(
//            videos = it,
//            onclickComment = {
////                navController.navigate(COMMENT_BOTTOM_SHEET_ROUTE)
//            },
//            onClickLike = { videoId, likeStatus -> },
//            onclickFavourite = {},
//            onClickAudio = {},
//            onClickUser = { userId -> }
//        )
    }
}