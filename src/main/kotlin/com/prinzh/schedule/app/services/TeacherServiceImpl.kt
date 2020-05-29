package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.TeacherRequest
import com.prinzh.schedule.app.services.interfaces.ITeacherService
import com.prinzh.schedule.domain.entity.Teacher
import com.prinzh.schedule.domain.repository.ITeacherRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class TeacherServiceImpl(private val repository: ITeacherRepository) : ITeacherService {
    override suspend fun getAll(): List<Teacher> {
        return repository.getAll()
    }

    override suspend fun getById(id: UUID): Teacher {
        return repository.getById(id) ?: throw NotFoundException()
    }

    override suspend fun create(data: TeacherRequest): Teacher {
        if (data.surname.isNullOrEmpty() || data.name.isNullOrEmpty() || data.patronymic.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        return repository.create(Teacher(
            surname = data.surname,
            name = data.name,
            patronymic = data.patronymic
        ))
    }

    override suspend fun update(id: UUID, data: TeacherRequest): Teacher {
        if (data.surname.isNullOrEmpty() || data.name.isNullOrEmpty() || data.patronymic.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        return repository.update(id, Teacher(
            surname = data.surname,
            name = data.name,
            patronymic = data.patronymic
        ))
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }
}