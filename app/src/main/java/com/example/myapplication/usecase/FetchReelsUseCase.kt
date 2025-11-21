package com.example.myapplication.usecase

import com.example.myapplication.bean.VideoItem
import com.example.myapplication.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchReelsUseCase @Inject constructor(
    private val repo: VideoRepository
) {
    operator fun invoke(): Flow<List<VideoItem>> {
        return repo.fetchVideos()
    }
}