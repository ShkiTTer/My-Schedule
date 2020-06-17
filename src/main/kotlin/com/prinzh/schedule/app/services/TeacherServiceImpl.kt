package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.TeacherRequest
import com.prinzh.schedule.app.responses.TeacherResponse
import com.prinzh.schedule.app.services.interfaces.ITeacherService
import com.prinzh.schedule.domain.entity.NewTeacher
import com.prinzh.schedule.domain.repository.ITeacherRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class TeacherServiceImpl(private val repository: ITeacherRepository) : ITeacherService {
    override suspend fun getAll(): List<TeacherResponse> {
        return repository.getAll().map {
            TeacherResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): TeacherResponse {
        return repository.getById(id)?.let {
            TeacherResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: TeacherRequest): TeacherResponse {
        if (data.surname.isNullOrEmpty() || data.name.isNullOrEmpty() || data.patronymic.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        return repository.create(
            NewTeacher(
                surname = data.surname,
                name = data.name,
                patronymic = data.patronymic
            )
        ).let {
            TeacherResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: TeacherRequest): TeacherResponse {
        if (data.surname.isNullOrEmpty() || data.name.isNullOrEmpty() || data.patronymic.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        return repository.update(
            id, NewTeacher(
                surname = data.surname,
                name = data.name,
                patronymic = data.patronymic
            )
        ).let {
            TeacherResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }

    override suspend fun search(query: String?): List<TeacherResponse> {
        if (query.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        val result = (repository.getBySurname(query).toSet()
                + repository.getByName(query).toSet()
                + repository.getByPatronymic(query).toSet())

        return result.map { TeacherResponse.fromDomain(it) }
    }

    override suspend fun getBySubject(subjectId: UUID): List<TeacherResponse> {
        return repository.getBySubject(subjectId).map {
            TeacherResponse.fromDomain(it)
        }
    }
}