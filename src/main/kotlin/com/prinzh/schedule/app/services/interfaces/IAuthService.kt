package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.LoginRequest
import com.prinzh.schedule.app.requests.TokenRequest
import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

interface IAuthService {
    suspend fun login(data: LoginRequest): IResponseContent
    suspend fun updateToken(userId: UUID, data: TokenRequest): IResponseContent
}