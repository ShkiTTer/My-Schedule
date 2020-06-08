package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent

data class LoginResponse(
    val user: UserResponse,
    val token: TokenResponse
) : IResponseContent