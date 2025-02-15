package com.mag1c.youtube.domain.bookmark.mapper

import com.mag1c.youtube.domain.bookmark.entity.BookmarkCategory
import com.mag1c.youtube.domain.user.entity.User

object BookmarkCategoryMapper {
    fun toEntity(categoryName: String, user: User): BookmarkCategory {
        return BookmarkCategory(
            name = categoryName,
            user = user
        )
    }
}