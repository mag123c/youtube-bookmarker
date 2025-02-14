package com.mag1c.youtube.domain.bookmark.dto

data class BookmarkRequest(
    val url: String,
    val category: String,
    val customComment: String?
)