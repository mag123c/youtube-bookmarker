package com.mag1c.youtube.domain.bookmark.dto

import jakarta.validation.constraints.NotBlank


data class BookmarkCategoryRequest(
    @field:NotBlank(message = "카테고리 이름을 입력해주세요.")
    val categoryName: String
)