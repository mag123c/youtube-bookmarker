package com.mag1c.youtube.domain.bookmark.entity

import com.mag1c.youtube.domain.user.entity.User
import com.mag1c.youtube.infra.database.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(
    name = "bookmark_category",
    indexes = [Index(name = "name", columnList = "name", unique = true)]
)
class BookmarkCategory(
    name: String,
    user: User
) : BaseEntity() {
    @Column
    var name: String = name

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user
}
