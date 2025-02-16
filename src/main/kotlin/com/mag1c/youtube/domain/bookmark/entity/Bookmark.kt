package com.mag1c.youtube.domain.bookmark.entity

import com.mag1c.youtube.domain.user.entity.User
import com.mag1c.youtube.infra.database.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(
    name = "bookmark",
    indexes = [Index(name = "name", columnList = "name", unique = true)]
)
class Bookmark(
    uuid: String,
    title: String,
    url: String,
    user: User,
    category: BookmarkCategory,
    thumbnailUrl: String?,
    customComment: String?,
    publishedAt: String?
) : BaseEntity() {
    @Column
    var uuid: String = uuid

    @Column
    var title: String = title

    @Column
    var url: String = url

    @Column
    var customComment: String? = customComment

    @Column
    var thumbnailUrl: String? = thumbnailUrl

    @Column
    var publishedAt: String? = publishedAt

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: BookmarkCategory = category
}
