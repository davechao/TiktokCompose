package com.example.myapplication.usecase

import com.example.myapplication.model.VideoItem
import com.example.myapplication.repository.VideoRepository
import javax.inject.Inject

class FetchReelsUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend operator fun invoke(): List<VideoItem> {
        return repository.getVideos()
    }
}