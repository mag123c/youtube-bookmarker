package com.mag1c.youtube.domain.bookmark.repository

import com.mag1c.youtube.domain.bookmark.entity.RedisBookmark
import com.mag1c.youtube.infra.redis.RedisHashRepositoryImpl
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class BookmarkRedisRepositoryTest @Autowired constructor(
    private val bookmarkRedisRepository: BookmarkRedisRepository
) : StringSpec({

    "Redis에 북마크를 저장한다." {
        // given
        val userId = "1233"
        val videoId = "V7v3z09m07s"
        val bookmark = RedisBookmark(
            id = "$userId:$videoId",
            userId = userId,
            videoId = videoId,
            title = "Test Video",
            thumbnailUrl = "https://test.com/test.png"
        )

        // when
        bookmarkRedisRepository.save(bookmark)
        val sut = bookmarkRedisRepository.findById(userId, videoId)

        // then
        sut shouldBe bookmark
    }
})