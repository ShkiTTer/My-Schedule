package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.LoginRequest
import com.prinzh.schedule.app.requests.UserRequest
import com.prinzh.schedule.app.responses.LoginResponse
import java.util.*

interface IUserService : ICrudService<UserRequest, UUID> {
    suspend fun login(data: LoginRequest): LoginResponse
}