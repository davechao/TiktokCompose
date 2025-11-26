package com.example.myapplication.ui.firenddetail

import com.example.myapplication.model.FriendItem

data class FriendDetailUiState(
    val isLoading: Boolean = false,
    val friend: FriendItem? = null,
)