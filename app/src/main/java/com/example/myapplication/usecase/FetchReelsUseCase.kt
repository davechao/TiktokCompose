package com.example.myapplication.usecase

import com.example.myapplication.model.VideoItem
import com.example.myapplication.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchReelsUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    operator fun invoke(): Flow<List<VideoItem>> {
        return repository.fetchVideos()
    }
}