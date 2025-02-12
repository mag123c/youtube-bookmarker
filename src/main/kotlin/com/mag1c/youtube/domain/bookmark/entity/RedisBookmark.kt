package com.mag1c.youtube.domain.bookmark.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

/**
 * Redis Key Prefix: bookmark:{userId}
 */
@RedisHash("bookmark")
data class RedisBookmark(
    @Id
    val id: String, //userId:videoId

    val userId: Int,
    val videoId: String,
    val title: String,
    val publishedAt: String,
    val thumbnailUrl: String,
    val tags: List<String>,
    val viewCount: Long,
    val likeCount: Long,
    val commentCount: Long,
    val category: String?,
    val customComment: String?,
) : Serializable