package com.mag1c.youtube.domain.bookmark.service

import com.mag1c.youtube.domain.bookmark.dto.BookmarkRequest
import com.mag1c.youtube.domain.bookmark.dto.BookmarkResponse
import com.mag1c.youtube.domain.bookmark.mapper.BookmarkMapper
import com.mag1c.youtube.infra.external.service.YoutubeMetadataService
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    private val youtubeMetadataService: YoutubeMetadataService,

    private val bookmarkCategoryService: BookmarkCategoryService,
) {
    /**
     * @API GET /bookmarks
     * 카테고리 기준으로 가져오기
     */
    fun getBookmarks(userId: Int, category: String): List<BookmarkResponse> {
        val key = "bookmark:$userId:category:$category"


    }

    /**
     * @API POST /bookmarks
     * 1. 저장할 영상 URL로 유튜브 메타데이터 조회 (3rd party GCP API)
     * 2. 레디스에 저장
     */
    fun saveBookmark(userId: Int, req: BookmarkRequest): BookmarkResponse {
        // 1. 북마크 정보 조회
        val youtubeMetadata = youtubeMetadataService.getMetadata(req.url)
        val savedEntity = BookmarkMapper.toEntity(userId, youtubeMetadata, req)
        return BookmarkMapper.toDto(redisBookmark)
    }

    /**
     * @API DELETE /bookmarks/:videoId
     */
    fun deleteBookmark(userId: Int, videoId: String) {
        bookmarkHashRepository.delete(userId, videoId)
    }


}