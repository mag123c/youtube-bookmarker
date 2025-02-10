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

    val userId: String,
    val videoId: String,
    val title: String,
    val thumbnailUrl: String,
    val comment: String? = null,
): Serializable