package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.LessonType
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object LessonTypes: UUIDTable("lesson_type") {
    val type = varchar("type", 50)
    val color = varchar("color", 8)
}

class LessonTypeEntity(id: EntityID<UUID>): UUIDEntity(id), IEntityConverter<LessonType> {
    companion object: UUIDEntityClass<LessonTypeEntity>(LessonTypes)

    var type by LessonTypes.type
    var color by LessonTypes.color

    override fun toDomain(): LessonType = LessonType(id.value, type, color)
}