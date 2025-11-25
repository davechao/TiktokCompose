package com.example.myapplication.datasource

import com.example.myapplication.model.FriendItem

interface FriendDataSource {
    suspend fun getUsers(): List<FriendItem>
}