package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.*
import com.prinzh.schedule.domain.entity.TeacherDiscipline
import com.prinzh.schedule.domain.repository.ITeacherDisciplineRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class TeacherDisciplineRepositoryImpl: ITeacherDisciplineRepository {
    override suspend fun getAll(): List<TeacherDiscipline> = dbQuery {
        TeacherDisciplineEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): TeacherDiscipline? = dbQuery {
        TeacherDisciplineEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: TeacherDiscipline): TeacherDiscipline = dbQuery {
        val teacher = TeacherEntity.findById(entity.teacher.id ?: throw NotFoundException())
            ?: throw NotFoundException()

        val subject = SubjectEntity.findById(entity.subject.id ?: throw NotFoundException())
            ?: throw NotFoundException()

        val type = LessonTypeEntity.findById(entity.lessonType.id ?: throw NotFoundException())
            ?: throw NotFoundException()

        TeacherDisciplineEntity.new {
            this.teacher = teacher
            this.subject = subject
            this.type = type
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: TeacherDiscipline): TeacherDiscipline = dbQuery {
        val discipline = TeacherDisciplineEntity.findById(id)
            ?: throw NotFoundException()

        val teacher = TeacherEntity.findById(entity.teacher.id ?: throw NotFoundException())
            ?: throw NotFoundException()

        val subject = SubjectEntity.findById(entity.subject.id ?: throw NotFoundException())
            ?: throw NotFoundException()

        val type = LessonTypeEntity.findById(entity.lessonType.id ?: throw NotFoundException())
            ?: throw NotFoundException()

        discipline.apply {
            this.teacher = teacher
            this.subject = subject
            this.type = type
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val discipline = TeacherDisciplineEntity.findById(id) ?: throw NotFoundException()
        discipline.delete()
    }
}