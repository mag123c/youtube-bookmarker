package com.mag1c.youtube.domain.bookmark.service

import org.springframework.stereotype.Service

@Service
class BookmarkCategoryService(
) {
    /**
     * @API GET /bookmarks/categories
     * 카테리 조회
     */

    /**
     * @API POST /bookmarks/categories
     * 카테고리 저장
     */
    fun saveCategory(userId: Int, category: String) {
        bookmarkZSetRepository.saveCategory(userId, category)
    }

    /**
     * @Internal ZSet에서 식별 키 조회
     */
    fun getBookmarkItemIdsByCategory(key: String): Set<String> {
        return bookmarkZSetRepository.getBookmarkItemIdsByCategory(key);
    }

    /**
     * @Internal ZSet에 식별 키 저장
     */
    fun saveItemId(userId: Int, videoId: String, category: String) {
        bookmarkZSetRepository.saveItemId(userId, videoId, category)
    }
}