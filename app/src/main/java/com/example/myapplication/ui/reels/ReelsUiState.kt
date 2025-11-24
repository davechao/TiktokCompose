package com.example.myapplication.ui.reels

import com.example.myapplication.model.VideoItem

data class ReelsUiState(
    val videos: List<VideoItem> = mutableListOf(),
)
