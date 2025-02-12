package com.mag1c.youtube.domain.bookmark.repository

import io.kotest.core.spec.style.StringSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class BookmarkRedisRepositoryTest @Autowired constructor(
    private val bookmarkRedisRepository: BookmarkRedisRepository
) : StringSpec({

})