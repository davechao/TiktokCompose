package com.example.myapplication.ui.friend

import com.example.myapplication.model.FriendItem


data class FriendsUiState(
    val friends: List<FriendItem> = mutableListOf(),
)
