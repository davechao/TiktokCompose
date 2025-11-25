package com.example.myapplication.repository

import com.example.myapplication.model.FriendItem

interface FriendRepository {
    suspend fun fetchFriends(): List<FriendItem>
}