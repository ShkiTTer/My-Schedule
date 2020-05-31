package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.Role
import com.prinzh.schedule.domain.entity.UserRole
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Roles: UUIDTable("role") {
    val role = enumeration("role", UserRole::class).uniqueIndex()
}

class RoleEntity(id: EntityID<UUID>): UUIDEntity(id), IEntityConverter<Role> {
    companion object: UUIDEntityClass<RoleEntity>(Roles)

    var role by Roles.role
    var users by UserEntity via UserRoles

    override fun toDomain(): Role = Role(role)
}