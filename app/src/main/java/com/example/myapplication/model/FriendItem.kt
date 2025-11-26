package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class FriendItem(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("blog") val blog: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("bio") val bio: String?,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int,
)
