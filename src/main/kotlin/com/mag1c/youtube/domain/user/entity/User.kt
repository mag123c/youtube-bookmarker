package com.mag1c.youtube.domain.user.entity

import com.mag1c.youtube.infra.database.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class User(
    uuid: String,
    deviceInfo: String
) : BaseEntity() {

    @Column
    var uuid: String = uuid

    @Column
    var deviceInfo: String = deviceInfo
}