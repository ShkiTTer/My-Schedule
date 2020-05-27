package com.prinzh.schedule.data.db.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Faculties : UUIDTable("faculty") {
    val title = varchar("title", 255)
}

class FacultyEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<FacultyEntity>(Faculties)

    var title by Faculties.title
}