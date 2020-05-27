package com.prinzh.schedule.data.db.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Teachers: UUIDTable("teacher") {
    val surname = varchar("surname", 50)
    val name = varchar("name", 50)
    val patronymic = varchar("patronymic", 50)
}

class TeacherEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<TeacherEntity>(Teachers)

    var surname by Teachers.surname
    var name by Teachers.name
    var patronymic by Teachers.patronymic
}