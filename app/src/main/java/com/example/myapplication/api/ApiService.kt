package com.example.myapplication.api

import com.example.myapplication.model.FriendItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun getUsers(): List<FriendItem>

    @GET("/user/{accountId}")
    suspend fun getUserDetail(@Path("accountId") id: Int): FriendItem
}