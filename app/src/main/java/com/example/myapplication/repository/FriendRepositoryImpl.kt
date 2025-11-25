package com.example.myapplication.repository

import com.example.myapplication.datasource.FriendDataSource
import com.example.myapplication.model.FriendItem
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val dataSource: FriendDataSource
) : FriendRepository {
    override suspend fun fetchFriends(): List<FriendItem> {
        return dataSource.getUsers()
    }
}