package com.prinzh.schedule.data.db.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object Groups: UUIDTable("group") {
    val title = varchar("title", 50)
    val faculty = reference("faculty", Faculties, ReferenceOption.CASCADE)
    val parentGroup = reference("parent_group", Groups, ReferenceOption.SET_NULL).nullable()
}

class GroupEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<GroupEntity>(Groups)

    var title by Groups.title
    var faculty by FacultyEntity referencedOn Groups.faculty
    var parentGroup by GroupEntity optionalReferencedOn Groups.parentGroup
}