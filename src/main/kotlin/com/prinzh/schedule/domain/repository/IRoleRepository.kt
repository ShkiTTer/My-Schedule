package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.Role
import java.util.*

interface IRoleRepository {
    suspend fun getAll(): List<Role>
    suspend fun getById(id: UUID): Role?
}