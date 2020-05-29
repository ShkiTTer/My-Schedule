package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.AudienceEntity
import com.prinzh.schedule.data.db.entity.BuildingEntity
import com.prinzh.schedule.domain.entity.Audience
import com.prinzh.schedule.domain.entity.Building
import com.prinzh.schedule.domain.repository.IAudienceRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class AudienceRepositoryImpl : IAudienceRepository {
    override suspend fun getAll(): List<Audience> = dbQuery {
        AudienceEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Audience? = dbQuery {
        AudienceEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: Audience): Audience = dbQuery {
        val building = BuildingEntity.findById(entity.building.id ?: throw NotFoundException())
            ?: throw NotFoundException()

        AudienceEntity.new {
            this.audienceNumber = entity.audienceNumber
            this.building = building
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: Audience): Audience = dbQuery {
        val audience = AudienceEntity.findById(id)
            ?: throw NotFoundException()

        val building = BuildingEntity.findById(entity.building.id ?: throw NotFoundException())
            ?: throw NotFoundException()

        audience.apply {
            this.audienceNumber = entity.audienceNumber
            this.building = building
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val audience = AudienceEntity.findById(id) ?: throw NotFoundException()
        audience.delete()
    }
}