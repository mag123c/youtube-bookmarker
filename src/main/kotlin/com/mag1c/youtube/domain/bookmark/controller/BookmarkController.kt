package com.mag1c.youtube.domain.bookmark.controller

import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.entity.RedisBookmark
import com.mag1c.youtube.domain.bookmark.mapper.BookmarkMapper
import com.mag1c.youtube.domain.bookmark.repository.BookmarkRedisRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookmarkController(
    @Autowired val repo: BookmarkRedisRepository
) {
    @GetMapping("health")
    fun health(): String {
        return "UP"
    }

    @PostMapping("test")
    fun test(@RequestBody req: BookmarkRequest) {
        val bookmark = BookmarkMapper.toEntity(req)
        repo.save(bookmark)
    }
}
