package com.example.myapplication.repository

import com.example.myapplication.datasource.VideoDataSource
import com.example.myapplication.model.VideoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepository @Inject constructor() {
    fun fetchVideos(): Flow<List<VideoItem>> {
        return VideoDataSource.fetchVideos()
    }
}