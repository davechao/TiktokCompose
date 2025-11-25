package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class FriendItem(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("type") val type: String?
)
