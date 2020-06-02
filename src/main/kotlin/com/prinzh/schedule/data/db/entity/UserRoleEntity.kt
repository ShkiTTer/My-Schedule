package com.prinzh.schedule.data.db.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object UserRoles: UUIDTable("user_role") {
    val user = reference("user", Users)
    val role = reference("role", Roles)
}

class UserRoleEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<UserRoleEntity>(UserRoles)

    var user by UserEntity referencedOn UserRoles.user
    var role by RoleEntity referencedOn UserRoles.role
}