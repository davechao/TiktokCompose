package com.example.myapplication.datasource

import com.example.myapplication.api.ApiService
import com.example.myapplication.model.FriendItem
import javax.inject.Inject

class FriendDataSourceImpl @Inject constructor(
    private val api: ApiService
) : FriendDataSource {

    override suspend fun getUsers(): List<FriendItem> {
        return api.getUsers()
    }

    override suspend fun getUser(id: Int): FriendItem {
        return api.getUserDetail(id)
    }
}