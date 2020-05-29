package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.BuildingRequest
import com.prinzh.schedule.app.services.interfaces.IBuildingService
import com.prinzh.schedule.domain.entity.Building
import com.prinzh.schedule.domain.repository.IBuildingRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class BuildingServiceImpl(private val repository: IBuildingRepository) : IBuildingService {
    override suspend fun getAll(): List<Building> {
        return repository.getAll()
    }

    override suspend fun getById(id: UUID): Building {
        return repository.getById(id) ?: throw NotFoundException()
    }

    override suspend fun create(data: BuildingRequest): Building {
        if (data.title.isNullOrEmpty() || data.address.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.create(Building(title = data.title, address = data.address))
    }

    override suspend fun update(id: UUID, data: BuildingRequest): Building {
        if (data.title.isNullOrEmpty() || data.address.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.update(id, Building(title = data.title, address = data.address))
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }
}