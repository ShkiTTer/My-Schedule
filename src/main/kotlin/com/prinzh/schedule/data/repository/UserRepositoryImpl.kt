package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.*
import com.prinzh.schedule.domain.entity.User
import com.prinzh.schedule.domain.repository.IUserRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import java.util.*

@KtorExperimentalAPI
class UserRepositoryImpl: IUserRepository {
    override suspend fun getAll(): List<User> = dbQuery {
        UserEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): User? = dbQuery {
        UserEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: User): User = dbQuery {
        val roles = mutableListOf<RoleEntity>()

        entity.roles.forEach {
            roles.add(RoleEntity.findById(it.id!!) ?: throw NotFoundException())
        }

        val user = UserEntity.new {
            login = entity.login
            password = entity.password
            mail = entity.mail
        }

        roles.forEach {
            UserRoleEntity.new {
                this.user = user
                role = it
            }
        }

        user.toDomain()
    }

    override suspend fun update(id: UUID, entity: User): User = dbQuery {
        val user = UserEntity.findById(id) ?: throw NotFoundException()
        val roles = mutableListOf<RoleEntity>()
        val deleteRoles = mutableListOf<RoleEntity>()
        val addRoles = mutableListOf<RoleEntity>()

        entity.roles.forEach {
            val role = RoleEntity.findById(it.id!!) ?: throw NotFoundException()
            roles.add(role)

            if (!user.roles.contains(role)) {
                addRoles.add(role)
            }
        }

        user.roles.forEach {
            if (!roles.contains(it)) {
                deleteRoles.add(it)
            }
        }

        deleteRoles.forEach {
            UserRoleEntity.find {
                (UserRoles.role eq it.id) and (UserRoles.user eq user.id)
            }.single().delete()
        }

        addRoles.forEach {
            UserRoleEntity.new {
                this.user = user
                this.role = it
            }
        }

        user.apply {
            login = entity.login
            password = entity.password
            mail = entity.mail
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val user = UserEntity.findById(id) ?: throw NotFoundException()
        user.delete()
    }
}