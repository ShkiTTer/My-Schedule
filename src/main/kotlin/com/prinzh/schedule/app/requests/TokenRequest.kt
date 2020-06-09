package com.prinzh.schedule.app.requests

data class TokenRequest(
    val accessToken: String?,
    val refreshToken: String?
)