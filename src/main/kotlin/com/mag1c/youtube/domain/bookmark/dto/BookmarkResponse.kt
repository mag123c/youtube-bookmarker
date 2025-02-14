package com.mag1c.youtube.domain.bookmark.dto

data class BookmarkResponse(
    val videoId: String,
    val title: String,
    val category: String,
    val customComment: String?,
    val thumbnailUrl: String,
    val publishedAt: String,
    val viewCount: Long? = 0,
)
