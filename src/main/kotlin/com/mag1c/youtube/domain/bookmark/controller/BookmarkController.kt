package com.mag1c.youtube.domain.troller

import com.mag1c.youtube.config.UserId
import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.dto.BookmarkResponse
import com.mag1c.youtube.domain.bookmark.service.BookmarkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bookmarks")
class BookmarkController(
    private val bookmarkService: BookmarkService
) {
    @GetMapping("/{categoryName}")
    fun getBookmarksByCategory(
        @UserId userId: Long,
        @PathVariable categoryName: String,
    ): ResponseEntity<List<BookmarkResponse>> {
        val bookmarks = bookmarkService.getBookmarksByCategory(userId, categoryName)
        return ResponseEntity.ok(bookmarks)
    }

    @PostMapping
    fun saveBookmark(
        @UserId userId: Long,
        @RequestBody req: BookmarkRequest
    ): ResponseEntity<Void> {
        bookmarkService.saveBookmark(userId, req)
        return ResponseEntity.noContent().build()
    }

//    @DeleteMapping
//    fun deleteCategory(
//        @UserId userId: Long,
//        @RequestBody req: BookmarkCategoryRequest
//    ): ResponseEntity<Void> {
//        bookmarkCategoryService.deleteCategory(userId, req)
//        return ResponseEntity.noContent().build()
//    }
}