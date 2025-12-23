package com.example.myapplication.navigation

sealed class RootGraph(val graph: String) {
    data object Reels : RootGraph("reels_graph")
    data object Friend : RootGraph("friend_graph")
}