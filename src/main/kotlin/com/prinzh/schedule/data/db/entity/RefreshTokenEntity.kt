package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.RefreshToken
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object RefreshTokens: UUIDTable("refresh_token") {
    val token = varchar("token", 64)
    val expired = long("expired")
    val user = reference("user", Users, ReferenceOption.CASCADE)
}

class RefreshTokenEntity(id: EntityID<UUID>): UUIDEntity(id), IEntityConverter<RefreshToken> {
    companion object: UUIDEntityClass<RefreshTokenEntity>(RefreshTokens)

    var token by RefreshTokens.token
    var expired by RefreshTokens.expired
    var user by UserEntity referencedOn RefreshTokens.user

    override fun toDomain(): RefreshToken = RefreshToken(
        id = id.value,
        token = token,
        expired = expired
    )
}