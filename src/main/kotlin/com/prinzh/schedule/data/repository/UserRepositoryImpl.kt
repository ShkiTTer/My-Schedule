package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.RoleEntity
import com.prinzh.schedule.data.db.entity.UserEntity
import com.prinzh.schedule.data.db.entity.Users
import com.prinzh.schedule.domain.entity.NewUser
import com.prinzh.schedule.domain.entity.User
import com.prinzh.schedule.domain.repository.IUserRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class UserRepositoryImpl : IUserRepository {
    override suspend fun getAll(): List<User> = dbQuery {
        UserEntity.all().map { it.toDomain() }
    }

    override suspend fun getById(id: UUID): User? = dbQuery {
        UserEntity.findById(id)?.toDomain()
    }

    override suspend fun create(entity: NewUser): User = dbQuery {
        val role = RoleEntity.findById(entity.role) ?: throw NotFoundException()

        UserEntity.new {
            this.login = entity.login
            this.password = entity.password
            this.mail = entity.mail
            this.salt = entity.salt
            this.role = role
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: NewUser): User = dbQuery {
        val user = UserEntity.findById(id) ?: throw NotFoundException()
        val role = RoleEntity.findById(entity.role) ?: throw NotFoundException()

        user.apply {
            this.login = entity.login
            this.password = entity.password
            this.mail = entity.mail
            this.salt = entity.salt
            this.role = role
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val user = UserEntity.findById(id) ?: throw NotFoundException()
        user.delete()
    }

    override suspend fun getByLogin(login: String): User? = dbQuery {
        UserEntity.find {
            Users.login eq login
        }.singleOrNull()?.toDomain()
    }

    override suspend fun getByMail(mail: String): User? = dbQuery {
        UserEntity.find {
            Users.mail eq mail
        }.singleOrNull()?.toDomain()
    }
}