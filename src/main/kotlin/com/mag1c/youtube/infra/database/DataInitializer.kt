package com.mag1c.youtube.infra.database

import com.mag1c.youtube.domain.bookmark.entity.BookmarkCategory
import com.mag1c.youtube.domain.bookmark.repository.BookmarkCategoryRepository
import com.mag1c.youtube.domain.user.entity.User
import com.mag1c.youtube.domain.user.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val bookmarkCategoryRepository: BookmarkCategoryRepository
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {
        val user = userRepository.findById(1).orElseGet {
            val newUser = User(
                uuid = "TEST",
                deviceInfo = "TEST"
            )
            userRepository.save(newUser)
        }

        val categories = listOf("개발", "예능", "게임")

        categories.forEach { categoryName ->
            if (!bookmarkCategoryRepository.existsByNameAndUser(categoryName, user)) {
                val category = BookmarkCategory(name = categoryName, user = user)
                bookmarkCategoryRepository.save(category)
            }
        }

        println("✅ 기본 데이터 설정 완료!")
    }
}
