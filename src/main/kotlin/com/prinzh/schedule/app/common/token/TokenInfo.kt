package com.prinzh.schedule.app.common.token

data class TokenInfo(
    val accessToken: String,
    val expiredAccess: Long,
    val refreshToken: String,
    val expiredRefresh: Long
)