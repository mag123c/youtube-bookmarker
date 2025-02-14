package com.mag1c.youtube.domain.bookmark.controller

import com.mag1c.youtube.config.UserId
import com.mag1c.youtube.domain.bookmark.dto.BookmarkCategoryRequest
import com.mag1c.youtube.domain.bookmark.dto.BookmarkCategoryResponse
import com.mag1c.youtube.domain.bookmark.service.BookmarkCategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/bookmarks/categories")
class BookmarkCategoryController (
    private val bookmarkCategoryService: BookmarkCategoryService
) {

    @GetMapping()
    fun getBookmarkCategories(
        @UserId userId: Int,
    ): ResponseEntity<List<BookmarkCategoryResponse>> {
        val categories: List<BookmarkCategoryResponse> = listOf(BookmarkCategoryResponse("TEST1"))
        return ResponseEntity.ok(categories)
    }

    @PostMapping()
    fun saveCategory(
        @UserId userId: Int,
        @RequestBody req: BookmarkCategoryRequest
    ): ResponseEntity<BookmarkCategoryResponse> {
        val category: BookmarkCategoryResponse = BookmarkCategoryResponse("TEST1")
        return ResponseEntity.created(URI.create(category.name)).body(category)
    }

    @DeleteMapping()
    fun deleteCategory(
        @UserId userId: Int,
        @PathVariable categoryName: String
    ): ResponseEntity.HeadersBuilder<*> {
        return ResponseEntity.noContent()
    }

}