package com.example.myapplication.ui.friend

import com.example.myapplication.model.FriendItem


data class FriendsUiState(
    val isLoading: Boolean = false,
    val friends: List<FriendItem> = mutableListOf(),
)
