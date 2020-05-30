package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.LessonTypeRequest
import com.prinzh.schedule.app.services.interfaces.ILessonTypeService
import com.prinzh.schedule.domain.entity.LessonType
import com.prinzh.schedule.domain.repository.ILessonTypeRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class LessonTypeServiceImpl(private val repository: ILessonTypeRepository) : ILessonTypeService {
    override suspend fun getAll(): List<LessonType> {
        return repository.getAll()
    }

    override suspend fun getById(id: UUID): LessonType {
        return repository.getById(id) ?: throw NotFoundException()
    }

    override suspend fun create(data: LessonTypeRequest): LessonType {
        if (data.type.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.create(LessonType(type = data.type))
    }

    override suspend fun update(id: UUID, data: LessonTypeRequest): LessonType {
        if (data.type.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.update(id, LessonType(type = data.type))
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }
}