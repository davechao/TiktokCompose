package com.example.myapplication.bean

import com.example.myapplication.util.extension.formattedCount
import com.example.myapplication.util.extension.randomUploadDate

data class VideoItem(
    val videoId: String,
    val authorDetails: UserItem,
    val videoStats: VideoStats,
    val videoLink: String,
    val description: String,
    val currentViewerInteraction: ViewerInteraction = ViewerInteraction(),
    val createdAt: String = randomUploadDate(),
    val audioModel: AudioItem? = null,
    val hasTag: List<HasTag> = listOf(),
) {
    data class VideoStats(
        var like: Long,
        var comment: Long,
        var share: Long,
        var favourite: Long,
        var views: Long = (like.plus(500)..like.plus(100000)).random()
    ) {
        var formattedLikeCount: String = ""
        var formattedCommentCount: String = ""
        var formattedShareCount: String = ""
        var formattedFavouriteCount: String = ""
        var formattedViewsCount: String = ""

        init {
            formattedLikeCount = like.formattedCount()
            formattedCommentCount = comment.formattedCount()
            formattedShareCount = share.formattedCount()
            formattedFavouriteCount = favourite.formattedCount()
            formattedViewsCount = views.formattedCount()
        }
    }

    data class HasTag(
        val id: Long,
        val title: String
    )

    data class ViewerInteraction(
        var isLikedByYou: Boolean = false,
        var isAddedToFavourite: Boolean = false
    )
}