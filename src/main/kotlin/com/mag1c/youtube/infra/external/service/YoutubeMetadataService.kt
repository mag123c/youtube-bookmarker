package com.mag1c.youtube.infra.external.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mag1c.youtube.infra.external.dto.YoutubeApiResponse
import com.mag1c.youtube.infra.external.dto.YoutubeVideoResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity
import java.net.URI

@Service
class YoutubeMetadataService(
    private val restClient: RestClient,
    @Value("\${youtube.api-key}") private val apiKey: String,
) {
    private val url: String = "https://www.googleapis.com/youtube/v3/videos"
    private val parts = listOf("snippet", "statistics")

    /**
     * 유튜브 서드파티 API로 영상 메타데이터 가져오기
     */
    fun getMetadata(url: String): YoutubeVideoResponseDto {
        val (videoId, timestamp) = parseYouTubeUrlWithUri(url)

        val result = try {
            restClient.get()
                .uri { uriBuilder ->
                    uriBuilder
                        .scheme("https")
                        .host("www.googleapis.com")
                        .path("/youtube/v3/videos")
                        .queryParam("id", videoId)
                        .queryParam("part", parts.joinToString(","))
                        .queryParam("hl", "ko")
                        .queryParam("key", apiKey)
                        .build()
                }
                .retrieve()
                .toEntity<String>()
        } catch (e: HttpClientErrorException) {
            throw IllegalArgumentException("YouTube API 요청 오류: ${e.statusCode} - ${e.responseBodyAsString}", e)
        } catch (e: HttpServerErrorException) {
            throw RuntimeException("YouTube API 서버 오류: ${e.statusCode} - ${e.responseBodyAsString}", e)
        } catch (e: Exception) {
            throw RuntimeException("YouTube API 호출 중 알 수 없는 오류 발생", e)
        }

        if (!result.statusCode.is2xxSuccessful) {
            throw RuntimeException("YouTube API 응답 실패: ${result.statusCode}")
        }

        val youtubeApiResponse: YoutubeApiResponse = jacksonObjectMapper()
            .readValue(result.body!!, YoutubeApiResponse::class.java)

        val item = youtubeApiResponse.items.firstOrNull()
            ?: throw IllegalArgumentException("YouTube API 응답에서 items가 비어있음!")

        return YoutubeVideoResponseDto.from(item, timestamp)
    }

    /**
     * 유튜브 URL에서 videoId 및 timestamp(선택)를 추출
     */
    private fun parseYouTubeUrlWithUri(url: String): Pair<String, String?> {
        val uri = URI(url)
        val queryParams = uri.query
            ?.split("&")
            ?.associate {
                val key = it.substringBefore("=")
                val value = it.substringAfter("=", "")
                key to value
            } ?: emptyMap()

        val videoId = queryParams["v"]
            ?: throw IllegalArgumentException("Invalid YouTube URL: Video ID not found")

        val timestamp = queryParams["t"]?.toString()

        return videoId to timestamp
    }

    fun sendMessage(name: String, message: String) {
        println("[$name] $message")
    }
}