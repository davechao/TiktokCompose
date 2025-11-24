package com.example.myapplication.repository

import com.example.myapplication.model.VideoItem
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun fetchVideos(): Flow<List<VideoItem>>
}