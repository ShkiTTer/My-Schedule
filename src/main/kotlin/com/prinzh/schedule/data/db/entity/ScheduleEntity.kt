package com.prinzh.schedule.data.db.entity

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

    val day = integer("day").check { column ->
        column.between(1, 7)
    }
    val time = varchar("time", 5)
    val weekStart = integer("week_start")
    val weekEnd = integer("week_end")
}

class ScheduleEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<ScheduleEntity>(Schedules)

    var group by GroupEntity referencedOn Schedules.group
    var discipline by TeacherDisciplineEntity referencedOn Schedules.discipline
    var audience by AudienceEntity referencedOn Schedules.audience

    var day by Schedules.day
    var time by Schedules.time
    var weekStart by Schedules.weekStart
    var weekEnd by Schedules.weekEnd
}