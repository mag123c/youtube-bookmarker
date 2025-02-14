package com.mag1c.youtube.infra.redis.zset

interface RedisZSetOperation<K, V> {
    fun add(key: K, value: V, score: Double) // ZADD
    fun getRange(key: K, start: Long, end: Long): Set<V> // ZRANGE
    fun remove(key: K, value: V) // ZREM
    fun getScore(key: K, value: V): Double? // ZSCORE
}
