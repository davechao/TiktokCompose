package com.example.myapplication.datasource

import com.example.myapplication.model.VideoItem

interface VideoDataSource {
    suspend fun getVideos(): List<VideoItem>
}