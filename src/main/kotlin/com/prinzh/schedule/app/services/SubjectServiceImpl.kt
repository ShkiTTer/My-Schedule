package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.SubjectRequest
import com.prinzh.schedule.app.responses.SubjectResponse
import com.prinzh.schedule.app.services.interfaces.ISubjectService
import com.prinzh.schedule.domain.entity.NewSubject
import com.prinzh.schedule.domain.repository.ISubjectRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class SubjectServiceImpl(private val repository: ISubjectRepository) : ISubjectService {
    override suspend fun getAll(): List<SubjectResponse> {
        return repository.getAll().map {
            SubjectResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): SubjectResponse {
        return repository.getById(id)?.let {
            SubjectResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: SubjectRequest): SubjectResponse {
        if (data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.create(NewSubject(title = data.title)).let {
            SubjectResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: SubjectRequest): SubjectResponse {
        if (data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.update(id, NewSubject(title = data.title)).let {
            SubjectResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }

    override suspend fun getByTeacher(teacherId: UUID): List<SubjectResponse> {
        return repository.getByTeacher(teacherId).map {
            SubjectResponse.fromDomain(it)
        }
    }
}