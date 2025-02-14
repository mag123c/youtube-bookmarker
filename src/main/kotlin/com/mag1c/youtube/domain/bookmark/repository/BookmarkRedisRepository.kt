package com.mag1c.youtube.domain.bookmark.repository

import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.entity.RedisBookmark
import com.mag1c.youtube.infra.redis.external.youtube.dto.YoutubeVideoResponseDto
import com.mag1c.youtube.infra.redis.hash.RedisHashOperation
import com.mag1c.youtube.infra.redis.zset.RedisZSetOperation
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.data.redis.serializer.SerializationException
import org.springframework.stereotype.Repository

@Repository
class BookmarkRedisRepository(
    private val redisHashRepository: RedisHashOperation<String, String, RedisBookmark>,
    private val redisZSetRepository: RedisZSetOperation<String, String>
) {

    /**
     * Hash에 metadata 저장
     */
    fun save(bookmark: RedisBookmark) {
        try {
            redisHashRepository.put(bookmark.id, bookmark.videoId, bookmark)
        } catch (e: RedisConnectionFailureException) {
            println("Redis 연결 오류 발생: ${e.message}")
            throw e;
        } catch (e: SerializationException) {
            println("Redis 데이터 역직렬화 오류 발생: ${e.message}")
            throw e;
        }
    }

    /**
     * ZSet에 식별 키만 저장
     */
    fun saveVideoId(bookmark: RedisBookmark) {
        try {
            redisZSetRepository.add(bookmark.id, bookmark.videoId, System.currentTimeMillis().toDouble())
        } catch (e: RedisConnectionFailureException) {
            println("Redis 연결 오류 발생: ${e.message}")
            throw e;
        } catch (e: SerializationException) {
            println("Redis 데이터 역직렬화 오류 발생: ${e.message}")
            throw e;
        }
    }

    /**
     * ZSet에서 식별 키로 items의 ID 반환
     */
    fun getBookmarkItemIdsByCategory(key: String): Set<String> {
        return redisZSetRepository.getRange(key, 0, -1)
    }

    /**
     * Hash에서 item ID로 데이터 반환
     */
    fun getBookmarkByItemId(key: String, itemId: String): RedisBookmark? {
        return try {
            redisHashRepository.get(key, itemId)
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
