package com.prinzh.schedule.data.db.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object RefreshTokens: UUIDTable("refresh_token") {
    val expired = long("expired")
    val user = reference("user", Users, ReferenceOption.CASCADE)
}

class RefreshTokenEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<RefreshTokenEntity>(RefreshTokens)

    var token by RefreshTokens.id
    var expired by RefreshTokens.expired
    var user by UserEntity referencedOn RefreshTokens.user
}