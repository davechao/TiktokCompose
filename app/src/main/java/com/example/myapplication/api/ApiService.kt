package com.example.myapplication.api

import com.example.myapplication.model.FriendItem
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun getUsers(): List<FriendItem>
}