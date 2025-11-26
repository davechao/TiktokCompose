package com.example.myapplication.ui.friend

import com.example.myapplication.model.FriendItem


data class FriendUiState(
    val isLoading: Boolean = false,
    val friends: List<FriendItem> = mutableListOf(),
)
