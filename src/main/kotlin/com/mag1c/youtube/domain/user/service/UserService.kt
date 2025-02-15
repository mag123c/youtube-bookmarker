package com.mag1c.youtube.domain.user.service

import com.mag1c.youtube.domain.user.entity.User
import com.mag1c.youtube.domain.user.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findUserById(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow { NotFoundException() }
    }
}
