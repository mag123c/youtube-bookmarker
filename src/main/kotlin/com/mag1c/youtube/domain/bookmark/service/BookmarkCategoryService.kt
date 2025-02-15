package com.mag1c.youtube.domain.bookmark.service

import com.mag1c.youtube.domain.bookmark.dto.BookmarkCategoryRequest
import com.mag1c.youtube.domain.bookmark.mapper.BookmarkCategoryMapper
import com.mag1c.youtube.domain.bookmark.repository.BookmarkCategoryRepository
import com.mag1c.youtube.domain.user.service.UserService
import org.springframework.stereotype.Service

@Service
class BookmarkCategoryService(
    private val bookmarkCategoryRepository: BookmarkCategoryRepository,

    private val userService: UserService
) {
    /**
     * @API GET /bookmarks/categories
     * 카테리 조회
     */
    fun getCategories(userId: Long): List<String> {
        val categories = bookmarkCategoryRepository.findByUserId(userId)
        return categories.map { it.name }
    }

    /**
     * @API POST /bookmarks/categories
     * 카테고리 저장
     */
    fun saveCategory(userId: Long, req: BookmarkCategoryRequest): String {
        val user = userService.findUserById(userId)
        val mappedToCategoryEntity = BookmarkCategoryMapper.toEntity(req.name, user)

        return bookmarkCategoryRepository.save(mappedToCategoryEntity).name
    }

    /**
     * @API DELETE /bookmarks/categories
     * 카테고리 삭제
     */
    fun deleteCategory(userId: Long, req: BookmarkCategoryRequest): Unit {
        val user = userService.findUserById(userId)
        val categoryEntity = bookmarkCategoryRepository.findByUserAndName(user, req.name)

        if (categoryEntity != null) {
            bookmarkCategoryRepository.delete(categoryEntity)
        }
    }
}