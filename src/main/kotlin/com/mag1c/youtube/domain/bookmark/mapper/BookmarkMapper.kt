package com.mag1c.youtube.domain.bookmark.mapper

import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.dto.BookmarkResponse
import com.mag1c.youtube.domain.bookmark.entity.Bookmark
import com.mag1c.youtube.domain.bookmark.entity.BookmarkCategory
import com.mag1c.youtube.domain.user.entity.User
import com.mag1c.youtube.infra.external.dto.YoutubeVideoResponseDto

object BookmarkMapper {

    fun createBookmark(
        user: User,
        category: BookmarkCategory,
        req: BookmarkRequest,
        metadata: BookmarkMetadata? = null
    ): Bookmark {
        return Bookmark(
            uuid = metadata?.uuid ?: "EMPTY",
            title = metadata?.title ?: "EMPTY",
            url = req.url,
            user = user,
            category = category,
            thumbnailUrl = metadata?.thumbnailUrl ?: "EMPTY",
            customComment = req.customComment,
            publishedAt = metadata?.publishedAt ?: "EMPTY"
        )
    }

    fun toYoutubeMetadataEntity(
        user: User,
        category: BookmarkCategory,
        req: BookmarkRequest,
        youtubeMetadata: YoutubeVideoResponseDto
    ): Bookmark {
        return createBookmark(
            user = user,
            category = category,
            req = req,
            metadata = BookmarkMetadata(
                uuid = youtubeMetadata.videoId,
                title = youtubeMetadata.title,
                thumbnailUrl = youtubeMetadata.thumbnailUrl,
                publishedAt = youtubeMetadata.publishedAt
            )
        )
    }

    fun toOtherMetadataEntity(
        user: User,
        category: BookmarkCategory,
        req: BookmarkRequest
    ): Bookmark {
        return createBookmark(
            user = user,
            category = category,
            req = req
        )
    }

    fun toDto(bookmark: Bookmark): BookmarkResponse {
        return BookmarkResponse(
            id = bookmark.id ?: throw IllegalArgumentException("Bookmark ID cannot be null"),
            uuid = bookmark.uuid,
            title = bookmark.title,
            url = bookmark.url,
            userId = bookmark.user.id ?: throw IllegalArgumentException("User ID cannot be null"),
            categoryName = bookmark.category.name,
            thumbnailUrl = bookmark.thumbnailUrl,
            customComment = bookmark.customComment,
            publishedAt = bookmark.publishedAt
        )
    }
}

data class BookmarkMetadata(
    val uuid: String,
    val title: String,
    val thumbnailUrl: String,
    val publishedAt: String
)
