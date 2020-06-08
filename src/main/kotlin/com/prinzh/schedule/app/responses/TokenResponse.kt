package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.common.token.TokenInfo
import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter

data class TokenResponse(
    val accessToken: String,
    val expired: Long,
    val refreshToken: String
) : IResponseContent {
    companion object : IResponseConverter<TokenInfo, TokenResponse> {
        override fun fromDomain(data: TokenInfo): TokenResponse = TokenResponse(
            accessToken = data.accessToken,
            expired = data.expiredAccess,
            refreshToken = data.refreshToken
        )
    }
}