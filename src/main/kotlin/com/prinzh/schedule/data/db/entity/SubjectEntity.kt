package com.prinzh.schedule.data.db.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Subjects: UUIDTable("subject") {
    val title = varchar("title", 200)
}

class SubjectEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<SubjectEntity>(Subjects)

    var title by Subjects.title
}