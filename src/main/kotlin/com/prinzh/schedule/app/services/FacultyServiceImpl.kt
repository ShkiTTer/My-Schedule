package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.FacultyRequest
import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.FacultyEntity
import com.prinzh.schedule.domain.entity.Faculty
import com.prinzh.schedule.app.services.interfaces.IFacultyService
import com.prinzh.schedule.domain.repository.IFacultyRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class FacultyServiceImpl(private val repository: IFacultyRepository) : IFacultyService {
    override suspend fun getAll(): List<Faculty> {
        return repository.getAll()
    }

    override suspend fun getById(id: UUID): Faculty {
        return repository.getById(id) ?: throw NotFoundException()
    }

    override suspend fun create(data: FacultyRequest): Faculty {
        if (data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.create(Faculty(title = data.title))
    }

    override suspend fun update(id: UUID, data: FacultyRequest): Faculty {
        if (data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.update(id, Faculty(title = data.title))
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }
}