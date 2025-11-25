package com.example.myapplication.usecase

import com.example.myapplication.model.FriendItem
import com.example.myapplication.repository.FriendRepository
import javax.inject.Inject

class FetchFriendsUseCase @Inject constructor(
    private val repository: FriendRepository
) {
    suspend operator fun invoke(): List<FriendItem> {
        return repository.fetchFriends()
    }
}