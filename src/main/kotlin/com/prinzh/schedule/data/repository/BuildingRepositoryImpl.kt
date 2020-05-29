package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.BuildingEntity
import com.prinzh.schedule.domain.entity.Building
import com.prinzh.schedule.domain.repository.IBuildingRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class BuildingRepositoryImpl : IBuildingRepository {
    override suspend fun getAll(): List<Building> = dbQuery {
        BuildingEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Building? = dbQuery {
        BuildingEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: Building): Building = dbQuery {
        BuildingEntity.new {
            title = entity.title
            address = entity.address
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: Building): Building = dbQuery {
        val building = BuildingEntity.findById(id) ?: throw NotFoundException()

        building.apply {
            title = entity.title
            address = entity.address
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val building = BuildingEntity.findById(id) ?: throw NotFoundException()

        building.delete()
    }
}