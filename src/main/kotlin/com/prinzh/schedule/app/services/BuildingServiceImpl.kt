package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.BuildingRequest
import com.prinzh.schedule.app.responses.BuildingResponse
import com.prinzh.schedule.app.services.interfaces.IBuildingService
import com.prinzh.schedule.domain.entity.NewBuilding
import com.prinzh.schedule.domain.repository.IBuildingRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class BuildingServiceImpl(private val repository: IBuildingRepository) : IBuildingService {
    override suspend fun getAll(): List<BuildingResponse> {
        return repository.getAll().map {
            BuildingResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): BuildingResponse {
        return repository.getById(id)?.let {
            BuildingResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: BuildingRequest): BuildingResponse {
        if (data.title.isNullOrEmpty() || data.address.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.create(NewBuilding(title = data.title, address = data.address)).let {
            BuildingResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: BuildingRequest): BuildingResponse {
        if (data.title.isNullOrEmpty() || data.address.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

        return repository.update(id, NewBuilding(title = data.title, address = data.address)).let {
            BuildingResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        repository.delete(id)
    }
}