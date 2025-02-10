package com.mag1c.youtube.domain.bookmark.dto

data class BookmarkRequest(
    val userId: String,
    val videoId: String,
    val title: String,
    val thumbnailUrl: String,
    val comment: String?
)