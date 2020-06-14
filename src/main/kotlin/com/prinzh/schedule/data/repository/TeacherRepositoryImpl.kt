package com.prinzh.schedule.data.repository

import com.prinzh.schedule.app.common.extension.toRegexStringQuery
import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.TeacherEntity
import com.prinzh.schedule.data.db.entity.Teachers
import com.prinzh.schedule.domain.entity.NewTeacher
import com.prinzh.schedule.domain.entity.Teacher
import com.prinzh.schedule.domain.repository.ITeacherRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.lowerCase
import java.util.*

@KtorExperimentalAPI
class TeacherRepositoryImpl : ITeacherRepository {
    override suspend fun getAll(): List<Teacher> = dbQuery {
        TeacherEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Teacher? = dbQuery {
        TeacherEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: NewTeacher): Teacher = dbQuery {
        TeacherEntity.new {
            surname = entity.surname
            name = entity.name
            patronymic = entity.patronymic
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: NewTeacher): Teacher = dbQuery {
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

    override suspend fun getBySurname(surname: String): List<Teacher> = dbQuery {
        TeacherEntity.find {
            Teachers.surname.lowerCase() regexp surname.toRegexStringQuery()
        }.map { it.toDomain() }
    }

    override suspend fun getByName(name: String): List<Teacher> = dbQuery {
        TeacherEntity.find {
            Teachers.name.lowerCase() regexp name.toRegexStringQuery()
        }.map { it.toDomain() }
    }

    override suspend fun getByPatronymic(patronymic: String): List<Teacher> = dbQuery {
        TeacherEntity.find {
            Teachers.patronymic.lowerCase() regexp patronymic.toRegexStringQuery()
        }.map { it.toDomain() }
    }
}