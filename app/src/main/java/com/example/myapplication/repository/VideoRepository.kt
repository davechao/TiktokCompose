package com.example.myapplication.repository

import com.example.myapplication.model.VideoItem

interface VideoRepository {
    suspend fun getVideos(): List<VideoItem>
}