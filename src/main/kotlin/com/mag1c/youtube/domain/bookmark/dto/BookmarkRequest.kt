package com.mag1c.youtube.domain.bookmark.dto

data class BookmarkRequest(
    val url: String,
    val categoryName: String,
    val customComment: String?
)