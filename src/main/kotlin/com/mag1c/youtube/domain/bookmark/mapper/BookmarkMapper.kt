package com.mag1c.youtube.domain.bookmark.mapper

import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.dto.BookmarkResponse
import com.mag1c.youtube.domain.bookmark.entity.RedisBookmark
import com.mag1c.youtube.infra.redis.external.youtube.dto.YoutubeVideoResponseDto

class BookmarkMapper {
    companion object {
        fun toEntity(userId: Int, youtubeMetadata: YoutubeVideoResponseDto, req: BookmarkRequest): RedisBookmark {
            return RedisBookmark(
                id = "bookmark:${userId}:${req.category}",
                userId = userId,
                videoId = youtubeMetadata.videoId,
                title = youtubeMetadata.title,
                publishedAt = youtubeMetadata.publishedAt,
                thumbnailUrl = youtubeMetadata.thumbnailUrl,
                tags = youtubeMetadata.tags,
                viewCount = youtubeMetadata.viewCount,
                likeCount = youtubeMetadata.likeCount,
                commentCount = youtubeMetadata.commentCount,
                category = req.category,
                customComment = req.customComment,
            )
        }

        fun toDto(redisBookmark: RedisBookmark): BookmarkResponse {
            return BookmarkResponse(
                videoId = redisBookmark.videoId,
                title = redisBookmark.title,
                _category = redisBookmark.category,
                customComment = redisBookmark.customComment,
                thumbnailUrl = redisBookmark.thumbnailUrl,
                publishedAt = redisBookmark.publishedAt,
                viewCount = redisBookmark.viewCount
            )
        }
    }
}
