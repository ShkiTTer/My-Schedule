package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.SubjectRequest
import com.prinzh.schedule.app.services.interfaces.ISubjectService
import com.prinzh.schedule.domain.entity.Subject
import com.prinzh.schedule.domain.repository.ISubjectRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class SubjectServiceImpl(private val repository: ISubjectRepository) : ISubjectService {
    override suspend fun getAll(): List<Subject> {
        return repository.getAll()
    }

    override suspend fun getById(id: UUID): Subject {
        return repository.getById(id) ?: throw NotFoundException()
    }

    override suspend fun create(data: SubjectRequest): Subject {
        if (data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.create(Subject(title = data.title))
    }

    override suspend fun update(id: UUID, data: SubjectRequest): Subject {
        if (data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.update(id, Subject(title = data.title))
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }
}