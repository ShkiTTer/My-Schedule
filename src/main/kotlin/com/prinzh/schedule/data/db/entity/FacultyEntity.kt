package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.Faculty
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Faculties : UUIDTable("faculty") {
    val title = varchar("title", 255)
}

class FacultyEntity(id: EntityID<UUID>) : UUIDEntity(id), IEntityConverter<Faculty> {
    companion object : UUIDEntityClass<FacultyEntity>(Faculties)

    var title by Faculties.title
    val groups by GroupEntity referrersOn Groups.faculty

    override fun toDomain(): Faculty = Faculty(id.value, title)
}