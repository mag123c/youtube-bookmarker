package com.mag1c.youtube.infra.redis.external.youtube.dto

data class YoutubeVideoResponseDto(
    val videoId: String,
    val title: String,
    val channelTitle: String,
    val publishedAt: String,
    val thumbnailUrl: String,
    val tags: List<String>,
    val viewCount: Long,
    val likeCount: Long,
    val commentCount: Long,
    val seenTime: String?,
) {
    companion object {
        fun from(item: YoutubeVideoItem, timestamp: String): YoutubeVideoResponseDto {
            return YoutubeVideoResponseDto(
                videoId = item.id,
                title = item.snippet.localized.title ?: item.snippet.title,
                channelTitle = item.snippet.channelTitle,
                publishedAt = item.snippet.publishedAt,
                thumbnailUrl = item.snippet.thumbnails.medium.url,
                tags = item.snippet.tags,
                viewCount = item.statistics.viewCount?.toLongOrNull() ?: 0,
                likeCount = item.statistics.likeCount?.toLongOrNull() ?: 0,
                commentCount = item.statistics.commentCount?.toLongOrNull() ?: 0,
                seenTime = timestamp ?: null
            )
        }
    }
}
