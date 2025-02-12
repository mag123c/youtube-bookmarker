package com.mag1c.youtube.domain.bookmark.controller

import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.dto.BookmarkResponse
import com.mag1c.youtube.domain.bookmark.service.BookmarkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/bookmarks")
class BookmarkController(
    private val bookmarkService: BookmarkService
) {

    @GetMapping
    fun getBookmarks(
        @RequestHeader("userId", required = true) userId: Int
    ): ResponseEntity<Map<String, List<BookmarkResponse>>> {
        val bookmarks = bookmarkService.getBookmakrs(userId)
        return ResponseEntity.ok(bookmarks)
    }

    @GetMapping("/{videoId}")
    fun getBookmark(
        @RequestHeader("userId", required = true) userId: Int,
        @PathVariable videoId: String
    ): ResponseEntity<BookmarkResponse> {
        val bookmark = bookmarkService.getBookmark(userId, videoId)
        return ResponseEntity.ok(bookmark)
    }

    @PostMapping
    fun saveBookmark(
        @RequestHeader("userId", required = true) userId: Int,
        @RequestBody req: BookmarkRequest
    ): ResponseEntity<BookmarkResponse> {
        val redisBookmark = bookmarkService.saveBookmark(userId, req)
        return ResponseEntity.created(URI.create(redisBookmark.videoId)).body(redisBookmark)
    }

    @DeleteMapping
    fun deleteBookmark(
        @RequestHeader("userId", required = true) userId: Int,
        @PathVariable videoId: String
    ): ResponseEntity.HeadersBuilder<*> {
        bookmarkService.deleteBookmark(userId, videoId)
        return ResponseEntity.noContent()
    }
}
