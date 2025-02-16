package com.mag1c.youtube.domain.bookmark.repository

import com.mag1c.youtube.domain.bookmark.entity.Bookmark
import com.mag1c.youtube.domain.bookmark.entity.BookmarkCategory
import com.mag1c.youtube.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository : JpaRepository<Bookmark, Long> {
    fun findByUserAndCategory(user: User, category: BookmarkCategory): List<Bookmark>
}
