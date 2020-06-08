package com.prinzh.schedule.data.repository

import com.prinzh.schedule.data.db.common.DatabaseFactory.dbQuery
import com.prinzh.schedule.data.db.entity.RefreshTokenEntity
import com.prinzh.schedule.data.db.entity.UserEntity
import com.prinzh.schedule.domain.entity.NewRefreshToken
import com.prinzh.schedule.domain.entity.RefreshToken
import com.prinzh.schedule.domain.repository.IRefreshTokenRepository
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class RefreshTokenRepositoryImpl: IRefreshTokenRepository {
    override suspend fun create(entity: NewRefreshToken): RefreshToken = dbQuery {
        val user = UserEntity.findById(entity.userId) ?: throw NotFoundException()

        RefreshTokenEntity.new {
            this.token = entity.token
            this.expired = entity.expired
            this.user = user
        }.toDomain()
    }

    override suspend fun update(id: UUID, entity: NewRefreshToken): RefreshToken = dbQuery {
        val token = RefreshTokenEntity.findById(id) ?: throw NotFoundException()
        val user = UserEntity.findById(entity.userId) ?: throw NotFoundException()

        token.apply {
            this.token = entity.token
            this.expired = entity.expired
            this.user = user
        }.toDomain()
    }

    override suspend fun delete(id: UUID) = dbQuery {
        val token = RefreshTokenEntity.findById(id) ?: throw NotFoundException()
        token.delete()
    }
}