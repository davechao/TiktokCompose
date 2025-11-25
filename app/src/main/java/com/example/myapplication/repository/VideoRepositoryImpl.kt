package com.example.myapplication.repository

import com.example.myapplication.datasource.VideoDataSource
import com.example.myapplication.model.VideoItem
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val dataSource: VideoDataSource
) : VideoRepository {

    override suspend fun getVideos(): List<VideoItem> {
        return dataSource.getVideos()
    }
}