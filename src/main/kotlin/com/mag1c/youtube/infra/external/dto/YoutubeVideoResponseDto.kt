package com.mag1c.youtube.infra.external.dto

data class YoutubeVideoResponseDto(
    val videoId: String,
    val title: String,
    val channelTitle: String,
    val publishedAt: String,
    val thumbnailUrl: String,
    val tags: List<String>,
) {
    companion object {
        fun from(item: YoutubeVideoItem): YoutubeVideoResponseDto {
            return YoutubeVideoResponseDto(
                videoId = item.id,
                title = item.snippet.localized.title ?: item.snippet.title,
                channelTitle = item.snippet.channelTitle,
                publishedAt = item.snippet.publishedAt,
                thumbnailUrl = item.snippet.thumbnails.default.url,
                tags = item.snippet.tags,
            )
        }
    }
}
