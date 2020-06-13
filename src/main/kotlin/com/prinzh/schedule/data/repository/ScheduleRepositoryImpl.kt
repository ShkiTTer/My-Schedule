package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.*
import com.prinzh.schedule.domain.entity.NewSchedule
import com.prinzh.schedule.domain.entity.Schedule
import com.prinzh.schedule.domain.repository.IScheduleRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.and
import java.util.*

@KtorExperimentalAPI
class ScheduleRepositoryImpl : IScheduleRepository {
    override suspend fun getAll(): List<Schedule> = dbQuery {
        ScheduleEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Schedule? = dbQuery {
        ScheduleEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: NewSchedule): Schedule = dbQuery {
        val group = GroupEntity.findById(entity.groupId) ?: throw NotFoundException()
        val audience = AudienceEntity.findById(entity.audienceId) ?: throw NotFoundException()
        val lessonType = LessonTypeEntity.findById(entity.typeId) ?: throw NotFoundException()

        val discipline = TeacherDisciplineEntity.find {
            (TeacherDisciplines.subject eq entity.subjectId) and (TeacherDisciplines.teacher eq entity.teacherId)
        }.singleOrNull() ?: throw NotFoundException()

        ScheduleEntity.new {
            this.group = group
            this.discipline = discipline
            this.audience = audience
            this.type = lessonType
            this.day = entity.day
            this.lessonNumber = entity.lessonNumber
            this.weekStart = entity.weekStart
            this.weekEnd = entity.weekEnd
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: NewSchedule): Schedule = dbQuery {
        val schedule = ScheduleEntity.findById(id) ?: throw NotFoundException()

        val group = GroupEntity.findById(entity.groupId) ?: throw NotFoundException()
        val audience = AudienceEntity.findById(entity.audienceId) ?: throw NotFoundException()
        val lessonType = LessonTypeEntity.findById(entity.typeId) ?: throw NotFoundException()

        val discipline = TeacherDisciplineEntity.find {
            (TeacherDisciplines.subject eq entity.subjectId) and (TeacherDisciplines.teacher eq entity.teacherId)
        }.singleOrNull() ?: throw NotFoundException()

        schedule.apply {
            this.group = group
            this.discipline = discipline
            this.audience = audience
            this.type = lessonType
            this.day = entity.day
            this.lessonNumber = entity.lessonNumber
            this.weekStart = entity.weekStart
            this.weekEnd = entity.weekEnd
        }.toDomain()
    }

    override suspend fun delete(id: UUID) {
        val schedule = ScheduleEntity.findById(id) ?: throw NotFoundException()
        schedule.delete()
    }
}