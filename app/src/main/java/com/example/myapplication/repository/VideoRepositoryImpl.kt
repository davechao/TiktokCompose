package com.example.myapplication.repository

import com.example.myapplication.datasource.VideoDataSource
import com.example.myapplication.model.VideoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor() : VideoRepository {
    override fun fetchVideos(): Flow<List<VideoItem>> {
        return VideoDataSource.fetchVideos()
    }
}