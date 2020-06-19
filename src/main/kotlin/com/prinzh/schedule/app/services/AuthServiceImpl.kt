package com.prinzh.schedule.app.services

import com.prinzh.schedule.app.common.exception.UnauthorizedException
import com.prinzh.schedule.app.common.util.HashUtil
import com.prinzh.schedule.app.common.util.JWTUtil
import com.prinzh.schedule.app.requests.LoginRequest
import com.prinzh.schedule.app.requests.TokenRequest
import com.prinzh.schedule.app.responses.LoginResponse
import com.prinzh.schedule.app.responses.TokenResponse
import com.prinzh.schedule.app.responses.UserResponse
import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.services.interfaces.IAuthService
import com.prinzh.schedule.domain.entity.NewRefreshToken
import com.prinzh.schedule.domain.repository.IRefreshTokenRepository
import com.prinzh.schedule.domain.repository.IUserRepository
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
class AuthServiceImpl(
    private val userRepository: IUserRepository,
    private val refreshTokenRepository: IRefreshTokenRepository
) : IAuthService {
    override suspend fun login(data: LoginRequest): LoginResponse {
        if (data.login.isNullOrEmpty() || data.password.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        val user = userRepository.getByLogin(data.login) ?: throw NotFoundException()

        if (user.password != HashUtil.hash(data.password, user.salt))
            throw BadRequestException("Invalid password or login")

        val tokenInfo = JWTUtil.newToken(user)
        refreshTokenRepository.create(
            NewRefreshToken(token = tokenInfo.refreshToken, expired = tokenInfo.expiredRefresh, userId = user.id)
        )

        return LoginResponse(
            user = UserResponse.fromDomain(user),
            token = TokenResponse.fromDomain(tokenInfo)
        )
    }

    override suspend fun updateToken(userId: UUID, data: TokenRequest): IResponseContent {
        if (data.refreshToken.isNullOrEmpty())
            throw BadRequestException("Invalid credentials")

        val user = userRepository.getById(userId) ?: throw NotFoundException()

        val refreshToken = user.tokens.find {
            it.token == data.refreshToken
        } ?: throw NotFoundException()

        if (refreshToken.expired < Date().time) {
            refreshTokenRepository.delete(refreshToken.id)
            throw UnauthorizedException()
        }

        val newToken = JWTUtil.newToken(user)

        refreshTokenRepository.update(
            refreshToken.id, NewRefreshToken(
                token = newToken.refreshToken,
                expired = newToken.expiredRefresh,
                userId = user.id
            )
        )

        return TokenResponse(
            accessToken = newToken.accessToken,
            expired = newToken.expiredAccess,
            refreshToken = newToken.refreshToken
        )
    }
}