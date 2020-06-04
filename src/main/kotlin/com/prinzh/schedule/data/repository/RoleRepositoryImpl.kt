package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.RoleEntity
import com.prinzh.schedule.domain.entity.Role
import com.prinzh.schedule.domain.repository.IRoleRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class RoleRepositoryImpl: IRoleRepository {
    override suspend fun getAll(): List<Role> = dbQuery {
        RoleEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): Role? = dbQuery {
        RoleEntity.findById(id)?.toDomain()
    }
}