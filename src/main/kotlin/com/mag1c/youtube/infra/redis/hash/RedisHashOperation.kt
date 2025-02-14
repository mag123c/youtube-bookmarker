package com.mag1c.youtube.infra.redis.hash

interface RedisHashOperation<K, HK, HV> {
    fun put(key: K, hashKey: HK, value: HV) //HSET
    fun get(key: K, hashKey: HK): HV? // HGET
    fun delete(key: K, hashKey: HK) //HDEL
    fun findAll(key: K): Map<HK, HV>
}