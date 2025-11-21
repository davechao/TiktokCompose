package com.example.myapplication.bean

data class AudioItem(
    val audioCoverImage: String,
    val isOriginal: Boolean,
    val audioAuthor: UserItem,
    val numberOfPost: Long,
    val originalVideoUrl: String,
)