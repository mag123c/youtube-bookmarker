package com.mag1c.youtube.domain.bookmark.repository

import com.mag1c.youtube.domain.bookmark.entity.RedisBookmark
import com.mag1c.youtube.infra.redis.RedisHashRepository
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.data.redis.serializer.SerializationException
import org.springframework.stereotype.Repository

@Repository
class BookmarkRedisRepository(
    private val redisHashRepository: RedisHashRepository<String, String, RedisBookmark>
) {
    fun save(bookmark: RedisBookmark) {
        try {
            println("SAVE SUCCESS $bookmark")
            redisHashRepository.put("bookmark:${bookmark.userId}", bookmark.videoId, bookmark)
        } catch (e: RedisConnectionFailureException) {
            println("Redis 연결 오류 발생: ${e.message}")
            throw e;
        } catch (e: SerializationException) {
            println("Redis 데이터 역직렬화 오류 발생: ${e.message}")
            throw e;
        }
    }

    fun findAll(userId: Int): Map<String, RedisBookmark> {
        return try {
            redisHashRepository.findAll("bookmark:$userId")
        } catch (e: RedisConnectionFailureException) {
            println("Redis 연결 오류 발생: ${e.message}")
            throw e;
        } catch (e: SerializationException) {
            println("Redis 데이터 역직렬화 오류 발생: ${e.message}")
            throw e;
        }
    }

    fun findByVideoId(userId: Int, videoId: String): RedisBookmark? {
        return try {
            redisHashRepository.get("bookmark:$userId", videoId)
        } catch (e: RedisConnectionFailureException) {
            println("Redis 연결 오류 발생: ${e.message}")
            throw e;
        } catch (e: SerializationException) {
            println("Redis 데이터 역직렬화 오류 발생: ${e.message}")
            throw e;
        }
    }

    fun delete(userId: Int, videoId: String) {
        redisHashRepository.delete("bookmark:$userId", videoId)
    }
}
