package com.mag1c.youtube.infra.redis.hash

import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisHashRepositoryImpl<K: Any, HK: Any, HV : Any>(
    private val redisTemplate: RedisTemplate<K, HV>
): RedisHashOperation<K, HK, HV> {

    private val hashOperations: HashOperations<K, HK, HV> = redisTemplate.opsForHash()

    override fun put(key: K, hashKey: HK, value: HV) {
        hashOperations.put(key, hashKey, value)
    }

    override fun get(key: K, hashKey: HK): HV? {
        return hashOperations.get(key, hashKey)
    }

    override fun delete(key: K, hashKey: HK) {
        hashOperations.delete(key, hashKey)
    }

    override fun findAll(key: K): Map<HK, HV> {
        return hashOperations.entries(key)
    }


}
