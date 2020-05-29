package com.prinzh.schedule.data.services

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.AudienceEntity
import com.prinzh.schedule.domain.entity.Audience
import com.prinzh.schedule.domain.services.IAudienceService
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class AudienceServiceImpl: IAudienceService {
    override suspend fun getAll(): List<Audience> = dbQuery {
        AudienceEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Audience? = dbQuery {
        AudienceEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: Audience): Audience {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: UUID, entity: Audience): Audience {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: UUID) {
        TODO("Not yet implemented")
    }
}