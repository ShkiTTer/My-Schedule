package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.FacultyRequest
import com.prinzh.schedule.app.responses.FacultyResponse
import com.prinzh.schedule.app.services.interfaces.IFacultyService
import com.prinzh.schedule.domain.entity.NewFaculty
import com.prinzh.schedule.domain.repository.IFacultyRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class FacultyServiceImpl(private val repository: IFacultyRepository) : IFacultyService {
    override suspend fun getAll(): List<FacultyResponse> {
        return repository.getAll().map {
            FacultyResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): FacultyResponse {
        return repository.getById(id)?.let {
            FacultyResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: FacultyRequest): FacultyResponse {
        if (data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.create(NewFaculty(title = data.title)).let {
            FacultyResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: FacultyRequest): FacultyResponse {
        if (data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.update(id, NewFaculty(title = data.title)).let {
            FacultyResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }
}