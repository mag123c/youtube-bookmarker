package com.mag1c.youtube.domain.bookmark

import com.mag1c.youtube.domain.bookmark.entity.RedisBookmark
import com.mag1c.youtube.infra.redis.RedisHashRepository
import org.springframework.stereotype.Repository

@Repository
class BookmarkRedisRepository(
    private val redisHashRepository: RedisHashRepository<String, String, RedisBookmark>
) {
    fun save(bookmark: RedisBookmark) {
        redisHashRepository.put("bookmark:${bookmark.userId}", bookmark.videoId, bookmark)
    }

    fun findByUserId(userId: String): Map<String, RedisBookmark> {
        return redisHashRepository.findAll("bookmark:$userId")
    }

    fun findById(userId: String, videoId: String): RedisBookmark? {
        return redisHashRepository.get("bookmark:$userId", videoId)
    }

    fun delete(userId: String, videoId: String) {
        redisHashRepository.delete("bookmark:$userId", videoId)
    }
}
