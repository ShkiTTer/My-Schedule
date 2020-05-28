package com.prinzh.schedule.data.services

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.SubjectEntity
import com.prinzh.schedule.domain.entity.Subject
import com.prinzh.schedule.domain.services.ISubjectService
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class SubjectServiceImpl: ISubjectService {
    override suspend fun getAll(): List<Subject> = dbQuery {
        SubjectEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Subject? = dbQuery {
        SubjectEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: Subject): Subject = dbQuery {
        SubjectEntity.new {
            title = entity.title
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: Subject): Subject = dbQuery {
        val subject = SubjectEntity.findById(id) ?: throw NotFoundException()

        subject.apply {
            title = entity.title
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val subject = SubjectEntity.findById(id) ?: throw NotFoundException()

        subject.delete()
    }
}