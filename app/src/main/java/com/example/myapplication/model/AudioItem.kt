package com.example.myapplication.model

data class AudioItem(
    val audioCoverImage: String,
    val isOriginal: Boolean,
    val audioAuthor: UserItem,
    val numberOfPost: Long,
    val originalVideoUrl: String,
)