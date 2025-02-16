package com.mag1c.youtube.domain.bookmark.dto

data class BookmarkResponse(
    val id: Long,
    val uuid: String,
    val title: String,
    val url: String,
    val userId: Long,
    val categoryName: String,
    val thumbnailUrl: String?,
    val customComment: String?,
    val publishedAt: String?
)
