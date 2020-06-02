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

    override suspend fun create(entity: Role): Role = dbQuery {
        RoleEntity.new {
            role = entity.role
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: Role): Role = dbQuery {
        val role = RoleEntity.findById(id) ?: throw NotFoundException()

        role.apply {
            this.role = entity.role
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val role = RoleEntity.findById(id) ?: throw NotFoundException()
        role.delete()
    }
}