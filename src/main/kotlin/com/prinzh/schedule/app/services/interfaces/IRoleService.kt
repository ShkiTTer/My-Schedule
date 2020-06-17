package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.responses.RoleResponse

interface IRoleService {
    suspend fun getAll(): List<RoleResponse>
}