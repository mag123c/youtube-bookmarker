package com.mag1c.youtube.infra.redis.zset

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisZSetRepositoryImpl(
    private val redisTemplate: StringRedisTemplate
) : RedisZSetOperation<String, String> {

    private val opsForZSet = redisTemplate.opsForZSet()

    override fun add(key: String, value: String, score: Double) {
        opsForZSet.add(key, value, score)
    }

    override fun getRange(key: String, start: Long, end: Long): Set<String> {
        return opsForZSet.range(key, start, end) ?: emptySet()
    }

    override fun remove(key: String, value: String) {
        opsForZSet.remove(key, value)
    }

    override fun getScore(key: String, value: String): Double? {
        return opsForZSet.score(key, value)
    }
}
