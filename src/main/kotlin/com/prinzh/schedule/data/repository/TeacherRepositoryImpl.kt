package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.TeacherEntity
import com.prinzh.schedule.domain.entity.Teacher
import com.prinzh.schedule.domain.repository.ITeacherRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class TeacherRepositoryImpl: ITeacherRepository {
    override suspend fun getAll(): List<Teacher> = dbQuery {
        TeacherEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Teacher? = dbQuery {
        TeacherEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: Teacher): Teacher = dbQuery {
        TeacherEntity.new {
            surname = entity.surname
            name = entity.name
            patronymic = entity.patronymic
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: Teacher): Teacher = dbQuery {
        val teacher = TeacherEntity.findById(id) ?: throw NotFoundException()

        teacher.apply {
            surname = entity.surname
            name = entity.name
            patronymic = entity.patronymic
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val teacher = TeacherEntity.findById(id) ?: throw NotFoundException()

        teacher.delete()
    }

}