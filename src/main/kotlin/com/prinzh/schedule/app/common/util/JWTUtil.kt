package com.prinzh.schedule.app.common.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.prinzh.schedule.app.common.token.TokenInfo
import com.prinzh.schedule.domain.entity.User
import java.security.SecureRandom
import java.util.*

object JWTUtil {
    private const val SECRET = "Zg457XVYg0v8ipxUThN5gutVon5xSGYabowGd9MEVfQRZP1mil3f3KfO4PSZkaCb"
    private const val ISSUER = "My schedule"
    private const val REFRESH_LENGTH = 32
    private const val ACCESS_VALIDITY_IN_MS = 18_000_00L // 0.5 hour
    private const val REFRESH_VALIDITY_IN_MS = 36_000_000_00L // 1000 hours

    private val random = SecureRandom()
    private val algorithm = Algorithm.HMAC512(SECRET)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .build()

    private fun getExpired(validity: Long) = Date(System.currentTimeMillis() + validity)

    private fun generateAccessToken(user: User, expired: Date): String = JWT.create()
        .withIssuer(ISSUER)
        .withExpiresAt(expired)
        .withClaim("id", user.id.toString())
        .withClaim("role", user.role.id.toString())
        .sign(algorithm)

    private fun generateRefreshToken(): String {
        val bytes = ByteArray(REFRESH_LENGTH)
        random.nextBytes(bytes)

        return Base64.getEncoder().encodeToString(bytes)
    }

    fun newToken(user: User): TokenInfo {
        val expiredAccess = getExpired(ACCESS_VALIDITY_IN_MS)
        val expiredRefresh = getExpired(REFRESH_VALIDITY_IN_MS)

        val accessToken = generateAccessToken(user, expiredAccess)
        val refreshToken = generateRefreshToken()

        return TokenInfo(
            accessToken = accessToken,
            expiredAccess = expiredAccess.time,
            refreshToken = refreshToken,
            expiredRefresh = expiredRefresh.time
        )
    }
}