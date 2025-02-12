package com.mag1c.youtube.infra.redis.external.youtube.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeApiResponse(
    val items: List<YoutubeVideoItem>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeVideoItem(
    val id: String,
    val snippet: YoutubeSnippet,
    val statistics: YoutubeStatistics,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeSnippet(
    val title: String,
    val description: String?,
    val channelTitle: String,
    val publishedAt: String,
    val tags: List<String>,
    val thumbnails: YoutubeThumbnails,
    val localized: YoutubeLocalized
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeThumbnails(
    val medium: YoutubeThumbnailDetail
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeThumbnailDetail(
    val url: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeLocalized(
    val title: String,
    val description: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class YoutubeStatistics(
    val viewCount: String?,
    val likeCount: String?,
    val commentCount: String?
)