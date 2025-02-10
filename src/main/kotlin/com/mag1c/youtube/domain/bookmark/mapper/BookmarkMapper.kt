package com.mag1c.youtube.domain.bookmark.mapper

import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.entity.RedisBookmark

class BookmarkMapper {
    companion object {
        fun toEntity(request: BookmarkRequest): RedisBookmark {
            return RedisBookmark(
                id = "${request.userId}:${request.videoId}",
                userId = request.userId,
                videoId = request.videoId,
                title = request.title,
                thumbnailUrl = request.thumbnailUrl,
                comment = request.comment
            )
        }
    }
}
