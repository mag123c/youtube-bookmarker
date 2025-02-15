package com.mag1c.youtube.domain.bookmark.repository

import com.mag1c.youtube.domain.bookmark.entity.BookmarkCategory
import com.mag1c.youtube.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkCategoryRepository : JpaRepository<BookmarkCategory, Long> {
    fun existsByNameAndUser(name: String, user: User): Boolean
    fun findByUserId(userId: Long): List<BookmarkCategory>
    fun findByUserAndName(user: User, name: String): BookmarkCategory?
}
