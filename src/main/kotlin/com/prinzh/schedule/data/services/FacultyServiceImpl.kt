package com.prinzh.schedule.data.services

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.FacultyEntity
import com.prinzh.schedule.domain.entity.Faculty
import com.prinzh.schedule.domain.services.IFacultyService
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class FacultyServiceImpl : IFacultyService {
    override suspend fun getAll(): List<Faculty> = dbQuery {
        FacultyEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Faculty? = dbQuery {
        FacultyEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: Faculty): Faculty = dbQuery {
        FacultyEntity.new {
            title = entity.title
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: Faculty) = dbQuery {
        val faculty = FacultyEntity.findById(id) ?: throw NotFoundException()

        faculty.apply {
            title = entity.title
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val faculty = FacultyEntity.findById(id) ?: throw NotFoundException()
        faculty.delete()
    }
}