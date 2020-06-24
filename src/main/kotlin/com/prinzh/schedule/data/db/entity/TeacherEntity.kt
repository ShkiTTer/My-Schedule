package com.prinzh.schedule.data.db.entity

import com.prinzh.schedule.data.converter.IEntityConverter
import com.prinzh.schedule.domain.entity.Subject
import com.prinzh.schedule.domain.entity.Teacher
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Teachers : UUIDTable("teacher") {
    val surname = varchar("surname", 50)
    val name = varchar("name", 50)
    val patronymic = varchar("patronymic", 50)
}

class TeacherEntity(id: EntityID<UUID>) : UUIDEntity(id), IEntityConverter<Teacher> {
    companion object : UUIDEntityClass<TeacherEntity>(Teachers)

    var surname by Teachers.surname
    var name by Teachers.name
    var patronymic by Teachers.patronymic
    val subjects by SubjectEntity via TeacherDisciplines

    override fun toDomain() = Teacher(
        id.value,
        surname,
        name,
        patronymic,
        subjects.map { it.toDomainWithoutTeachers() }
    )

    fun toDomainWithoutSubjects() = Teacher(
        id.value,
        surname,
        name,
        patronymic,
        emptyList()
    )
}