package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.responses.RoleResponse
import com.prinzh.schedule.app.services.interfaces.IRoleService
import com.prinzh.schedule.domain.repository.IRoleRepository

class RoleServiceImpl(private val roleRepository: IRoleRepository):
    IRoleService {
    override suspend fun getAll(): List<RoleResponse> {
        return roleRepository.getAll().map {
            RoleResponse.fromDomain(it)
        }
    }
}