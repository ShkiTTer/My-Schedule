package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.requests.AudienceRequest
import com.prinzh.schedule.app.requests.BuildingRequest
import com.prinzh.schedule.app.services.interfaces.IAudienceService
import com.prinzh.schedule.domain.entity.Audience
import com.prinzh.schedule.domain.entity.Building
import com.prinzh.schedule.domain.repository.IAudienceRepository
import com.prinzh.schedule.domain.repository.IBuildingRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class AudienceServiceImpl(
    private val audienceRepository: IAudienceRepository,
    private val buildingRepository: IBuildingRepository
) : IAudienceService {
    override suspend fun getAll(): List<Audience> {
        return audienceRepository.getAll()
    }

    override suspend fun getById(id: UUID): Audience {
        return audienceRepository.getById(id) ?: throw NotFoundException()
    }

    override suspend fun create(data: AudienceRequest): Audience {
        if (data.audienceNumber.isNullOrEmpty() || data.building.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        val buildingId = try {
            UUID.fromString(data.building)
        } catch (e: Exception) {
            throw BadRequestException("Invalid credentials")
        }

        val building = buildingRepository.getById(buildingId) ?: throw NotFoundException()

        return audienceRepository.create(Audience(audienceNumber = data.audienceNumber, building = building))
    }

    override suspend fun update(id: UUID, data: AudienceRequest): Audience {
        if (data.audienceNumber.isNullOrEmpty() || data.building.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        val buildingId = try {
            UUID.fromString(data.building)
        } catch (e: Exception) {
            throw BadRequestException("Invalid credentials")
        }

        println("Here")
        val building = buildingRepository.getById(buildingId) ?: throw NotFoundException()
        println("Or Here")
        return audienceRepository.update(id, Audience(audienceNumber = data.audienceNumber, building = building))
    }

    override suspend fun delete(id: UUID) {
        audienceRepository.delete(id)
    }
}