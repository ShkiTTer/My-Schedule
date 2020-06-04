package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.SubjectEntity
import com.prinzh.schedule.domain.entity.NewSubject
import com.prinzh.schedule.domain.entity.Subject
import com.prinzh.schedule.domain.repository.ISubjectRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class SubjectRepositoryImpl: ISubjectRepository {
    override suspend fun getAll(): List<Subject> = dbQuery {
        SubjectEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Subject? = dbQuery {
        SubjectEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: NewSubject): Subject = dbQuery {
        SubjectEntity.new {
            title = entity.title
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: NewSubject): Subject = dbQuery {
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