package com.example.myapplication.datasource

import com.example.myapplication.datasource.data.VideoData
import com.example.myapplication.model.VideoItem
import javax.inject.Inject

class VideoDataSourceImpl @Inject constructor() : VideoDataSource {

    override suspend fun getVideos(): List<VideoItem> {
        return VideoData.videosList.shuffled()
    }
}