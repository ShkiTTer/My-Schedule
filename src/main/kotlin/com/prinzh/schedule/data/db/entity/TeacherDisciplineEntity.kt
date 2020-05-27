package com.prinzh.schedule.data.db.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object TeacherDisciplines: UUIDTable("teacher_discipline") {
    val teacher = reference("teacher", Teachers, ReferenceOption.CASCADE)
    val subject = reference("subject", Subjects, ReferenceOption.CASCADE)
    val type = reference("type", LessonTypes, ReferenceOption.CASCADE)
}

class TeacherDisciplineEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object: UUIDEntityClass<TeacherDisciplineEntity>(TeacherDisciplines)

    var teacher by TeacherEntity referencedOn TeacherDisciplines.teacher
    var subject by SubjectEntity referencedOn TeacherDisciplines.subject
    var type by LessonTypeEntity referencedOn TeacherDisciplines.type
}