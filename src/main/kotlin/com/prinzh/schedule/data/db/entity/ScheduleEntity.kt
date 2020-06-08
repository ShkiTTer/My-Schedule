package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.Schedule
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object Schedules : UUIDTable("schedule") {
    val group = reference("group", Groups, ReferenceOption.CASCADE)
    val discipline = reference("discipline", TeacherDisciplines, ReferenceOption.CASCADE)
    val audience = reference("audience", Audiences, ReferenceOption.CASCADE)
    val type = reference("type", LessonTypes, ReferenceOption.RESTRICT)

    val day = integer("day").check { column ->
        column.between(1, 6)
    }
    val lessonNumber = integer("lesson_number").check { column ->
        column.between(1, 7)
    }
    val weekStart = integer("week_start")
    val weekEnd = integer("week_end")
}

class ScheduleEntity(id: EntityID<UUID>) : UUIDEntity(id), IEntityConverter<Schedule> {
    companion object : UUIDEntityClass<ScheduleEntity>(Schedules)

    var group by GroupEntity referencedOn Schedules.group
    var discipline by TeacherDisciplineEntity referencedOn Schedules.discipline
    var audience by AudienceEntity referencedOn Schedules.audience
    var type by LessonTypeEntity referencedOn Schedules.type

    var day by Schedules.day
    var lessonNumber by Schedules.lessonNumber
    var weekStart by Schedules.weekStart
    var weekEnd by Schedules.weekEnd

    override fun toDomain(): Schedule = Schedule(
        id = id.value,
        group = group.toDomain(),
        discipline = discipline.toDomain(),
        audience = audience.toDomain(),
        type = type.toDomain(),
        day = day,
        lessonNumber = lessonNumber,
        weekStart = weekStart,
        weekEnd = weekEnd
    )
}