package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.SubjectEntity
import com.prinzh.schedule.data.db.entity.TeacherDisciplineEntity
import com.prinzh.schedule.data.db.entity.TeacherDisciplines
import com.prinzh.schedule.data.db.entity.TeacherEntity
import com.prinzh.schedule.domain.entity.NewTeacherDiscipline
import com.prinzh.schedule.domain.entity.TeacherDiscipline
import com.prinzh.schedule.domain.repository.ITeacherDisciplineRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class TeacherDisciplineRepositoryImpl : ITeacherDisciplineRepository {
    override suspend fun getAll(): List<TeacherDiscipline> = dbQuery {
        TeacherDisciplineEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): TeacherDiscipline? = dbQuery {
        TeacherDisciplineEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: NewTeacherDiscipline): TeacherDiscipline = dbQuery {
        val teacher = TeacherEntity.findById(entity.teacherId) ?: throw NotFoundException()
        val subject = SubjectEntity.findById(entity.subjectId) ?: throw NotFoundException()

        TeacherDisciplineEntity.new {
            this.teacher = teacher
            this.subject = subject
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: NewTeacherDiscipline): TeacherDiscipline = dbQuery {
        val discipline = TeacherDisciplineEntity.findById(id) ?: throw NotFoundException()

        val teacher = TeacherEntity.findById(entity.teacherId) ?: throw NotFoundException()
        val subject = SubjectEntity.findById(entity.subjectId) ?: throw NotFoundException()

        discipline.apply {
            this.teacher = teacher
            this.subject = subject
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val discipline = TeacherDisciplineEntity.findById(id) ?: throw NotFoundException()
        discipline.delete()
    }

    override suspend fun getByTeacher(teacherId: UUID): List<TeacherDiscipline> = dbQuery {
        TeacherDisciplineEntity.find {
            TeacherDisciplines.teacher eq teacherId
        }.map { it.toDomain() }
    }
}