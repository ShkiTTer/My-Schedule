package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.NewRefreshToken
import com.prinzh.schedule.domain.entity.RefreshToken
import java.util.*

interface IRefreshTokenRepository {
    suspend fun create(entity: NewRefreshToken): RefreshToken
    suspend fun update(id: UUID, entity: NewRefreshToken): RefreshToken
    suspend fun delete(id: UUID)
}