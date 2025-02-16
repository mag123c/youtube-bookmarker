package com.mag1c.youtube.domain.bookmark.service

import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.dto.BookmarkResponse
import com.mag1c.youtube.domain.bookmark.mapper.BookmarkMapper
import com.mag1c.youtube.domain.bookmark.repository.BookmarkRepository
import com.mag1c.youtube.domain.user.service.UserService
import com.mag1c.youtube.infra.external.service.YoutubeMetadataService
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    private val youtubeMetadataService: YoutubeMetadataService,
    private val userService: UserService,
    private val bookmarkCategoryService: BookmarkCategoryService,

    private val bookmarkRepository: BookmarkRepository
) {
    /**
     * @API GET /bookmarks/:categoryName
     */
    fun getBookmarksByCategory(userId: Long, categoryName: String): List<BookmarkResponse> {
        val user = userService.findUserById(userId)
        val category = bookmarkCategoryService.getCategory(user, categoryName)

        val bookmarks = bookmarkRepository.findByUserAndCategory(user, category)
        return bookmarks.map { BookmarkMapper.toDto(it) }
    }

    /**
     * @API POST /bookmarks
     */
    fun saveBookmark(userId: Long, req: BookmarkRequest) {
        // 기본 정보 조회
        val user = userService.findUserById(userId)
        val category = bookmarkCategoryService.getCategory(user, req.categoryName)

        val bookmark = when {
            req.url.contains("youtube") -> {
                val youtubeMetadata = youtubeMetadataService.getMetadata(req.url)
                BookmarkMapper.toYoutubeMetadataEntity(user, category, req, youtubeMetadata)
            }

            else -> BookmarkMapper.toOtherMetadataEntity(user, category, req)
        }

        bookmarkRepository.save(bookmark)
    }

    /**
     * @API DELETE /bookmarks/:videoId
     */
    fun deleteBookmark(userId: Int, videoId: String) {
    }


}