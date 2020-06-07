package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.common.extension.toColor
import com.prinzh.schedule.app.requests.LessonTypeRequest
import com.prinzh.schedule.app.responses.LessonTypeResponse
import com.prinzh.schedule.app.services.interfaces.ILessonTypeService
import com.prinzh.schedule.domain.entity.NewLessonType
import com.prinzh.schedule.domain.repository.ILessonTypeRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class LessonTypeServiceImpl(private val repository: ILessonTypeRepository) : ILessonTypeService {
    override suspend fun getAll(): List<LessonTypeResponse> {
        return repository.getAll().map {
            LessonTypeResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): LessonTypeResponse {
        return repository.getById(id)?.let {
            LessonTypeResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: LessonTypeRequest): LessonTypeResponse {
        if (data.type.isNullOrEmpty() || data.color.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.create(NewLessonType(type = data.type, color = data.color.toColor())).let {
            LessonTypeResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: LessonTypeRequest): LessonTypeResponse {
        if (data.type.isNullOrEmpty() || data.color.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.update(id, NewLessonType(type = data.type, color = data.color.toColor())).let {
            LessonTypeResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }
}