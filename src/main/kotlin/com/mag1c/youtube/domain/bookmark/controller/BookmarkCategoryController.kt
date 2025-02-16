package com.mag1c.youtube.domain.bookmark.controller

import com.mag1c.youtube.config.UserId
import com.mag1c.youtube.domain.bookmark.dto.BookmarkCategoryRequest
import com.mag1c.youtube.domain.bookmark.service.BookmarkCategoryService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bookmarks/categories")
class BookmarkCategoryController(
    private val bookmarkCategoryService: BookmarkCategoryService
) {
    @GetMapping
    fun getCategories(@UserId userId: Long): ResponseEntity<List<String>> {
        val categories = bookmarkCategoryService.getCategories(userId)
        return ResponseEntity.ok(categories)

    }

    @PostMapping
    fun saveCategory(
        @UserId userId: Long,
        @Valid @RequestBody req: BookmarkCategoryRequest
    ): ResponseEntity<Void> {
        bookmarkCategoryService.saveCategory(userId, req)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    fun deleteCategory(
        @UserId userId: Long,
        @RequestBody req: BookmarkCategoryRequest
    ): ResponseEntity<Void> {
        bookmarkCategoryService.deleteCategory(userId, req)
        return ResponseEntity.noContent().build()
    }
}