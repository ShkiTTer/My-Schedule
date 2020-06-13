package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.UserRequest
import com.prinzh.schedule.domain.entity.User
import java.util.*

interface IUserService : ICrudService<UserRequest, UUID> {
    suspend fun checkUser(userId: UUID, roleId: UUID): User?
}