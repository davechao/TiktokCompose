package com.example.myapplication.ui.video

import com.example.myapplication.model.VideoItem

data class VideoUiState(
    val videos: List<VideoItem> = mutableListOf(),
)
