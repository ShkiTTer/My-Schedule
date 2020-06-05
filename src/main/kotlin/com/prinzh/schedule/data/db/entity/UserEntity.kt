package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.User
import com.prinzh.schedule.domain.entity.UserRole
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Users : UUIDTable(name = "user") {
    val login = varchar("login", 50).uniqueIndex()
    val password = varchar("password", 255)
    val mail = varchar("mail", 150).uniqueIndex()
    val salt = binary("salt", 16)
}

class UserEntity(id: EntityID<UUID>): UUIDEntity(id), IEntityConverter<User> {
    companion object: UUIDEntityClass<UserEntity>(Users)

    var login by Users.login
    var password by Users.password
    var mail by Users.mail
    var salt by Users.salt
    var roles by RoleEntity via UserRoles

    override fun toDomain(): User = User(
        id = id.value,
        login = login,
        password = password,
        mail = mail,
        salt = salt,
        roles = roles.map { it.toDomain() }
    )
}