package com.mag1c.youtube.domain.user.repository

import com.mag1c.youtube.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
