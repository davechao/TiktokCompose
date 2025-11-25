package com.example.myapplication.datasource

import com.example.myapplication.api.ApiService
import com.example.myapplication.model.FriendItem
import javax.inject.Inject

class FriendDataSource @Inject constructor(
    private val api: ApiService
) {
    suspend fun getUsers(): List<FriendItem> {
        return api.getUsers()
    }
}