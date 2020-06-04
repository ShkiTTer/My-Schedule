package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.requests.AudienceRequest
import com.prinzh.schedule.app.responses.FullAudienceResponse
import com.prinzh.schedule.app.services.interfaces.IAudienceService
import com.prinzh.schedule.domain.entity.NewAudience
import com.prinzh.schedule.domain.repository.IAudienceRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class AudienceServiceImpl(
    private val audienceRepository: IAudienceRepository
) : IAudienceService {
    override suspend fun getAll(): List<FullAudienceResponse> {
        return audienceRepository.getAll().map {
            FullAudienceResponse.fromDomain(it)
        }
    }

    override suspend fun getById(id: UUID): FullAudienceResponse {
        return audienceRepository.getById(id)?.let {
            FullAudienceResponse.fromDomain(it)
        } ?: throw NotFoundException()
    }

    override suspend fun create(data: AudienceRequest): FullAudienceResponse {
        if (data.audienceNumber.isNullOrEmpty() || data.building.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        return audienceRepository.create(
            NewAudience(
                audienceNumber = data.audienceNumber,
                buildingId = data.building.toUUID()
            )
        ).let {
            FullAudienceResponse.fromDomain(it)
        }
    }

    override suspend fun update(id: UUID, data: AudienceRequest): FullAudienceResponse {
        if (data.audienceNumber.isNullOrEmpty() || data.building.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        return audienceRepository.update(
            id,
            NewAudience(
                audienceNumber = data.audienceNumber,
                buildingId = data.building.toUUID()
            )
        ).let {
            FullAudienceResponse.fromDomain(it)
        }
    }

    override suspend fun delete(id: UUID) {
        audienceRepository.delete(id)
    }
}