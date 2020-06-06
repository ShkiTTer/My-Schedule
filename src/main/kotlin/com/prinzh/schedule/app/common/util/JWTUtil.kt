package com.prinzh.schedule.app.common.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.prinzh.schedule.app.common.token.Token
import com.prinzh.schedule.domain.entity.User
import java.util.*

object JWTUtil {
    private const val SECRET = "Zg457XVYg0v8ipxUThN5gutVon5xSGYabowGd9MEVfQRZP1mil3f3KfO4PSZkaCb"
    private const val ISSUER = "My schedule"
    private const val REFRESH_LENGTH = 16
    private const val REFRESH_ADDITIONAL_LENGTH = 8
    private const val ACCESS_VALIDITY_IN_MS = 18_000_00L // 0.5 hour
    private const val REFRESH_VALIDITY_IN_MS = 36_000_000_00L // 1000 hours

    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val algorithm = Algorithm.HMAC512(SECRET)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .build()

    private fun getExpired(validity: Long) = Date(System.currentTimeMillis() + validity)

    private fun generateAccessToken(user: User, expired: Date): String = JWT.create()
        .withIssuer(ISSUER)
        .withExpiresAt(expired)
        .withClaim("id", user.id.toString())
        .withArrayClaim("roles", user.roles.map { it.id.toString() }.toTypedArray())
        .sign(algorithm)

    private fun generateRefreshToken(accessToken: String, expired: Date): String {
        val token = (1..REFRESH_LENGTH)
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")

        return token + accessToken.takeLast(REFRESH_ADDITIONAL_LENGTH)
    }

    fun newToken(user: User): Token {
        val expiredAccess = getExpired(ACCESS_VALIDITY_IN_MS)
        val expiredRefresh = getExpired(REFRESH_VALIDITY_IN_MS)

        val accessToken = generateAccessToken(user, expiredAccess)
        val refreshToken = generateRefreshToken(accessToken, expiredRefresh)

        return Token(
            accessToken = accessToken,
            expiredAccess = expiredAccess.time,
            refreshToken = refreshToken,
            expiredRefresh = expiredRefresh.time
        )
    }

    fun validateRefreshToken(accessToken: String, refreshToken: String): Boolean {
        return accessToken.takeLast(REFRESH_ADDITIONAL_LENGTH) == refreshToken.takeLast(REFRESH_ADDITIONAL_LENGTH)
    }
}