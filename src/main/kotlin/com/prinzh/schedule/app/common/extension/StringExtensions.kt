package com.prinzh.schedule.app.common.extension

import com.prinzh.schedule.domain.entity.UserRole
import io.ktor.features.BadRequestException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
fun String?.toUUID(): UUID = try {
    UUID.fromString(this)
} catch (t: Throwable) {
    throw BadRequestException("Invalid credentials")
}

@KtorExperimentalAPI
fun String?.toUserRole(): UserRole = try {
    UserRole.valueOf(this ?: throw BadRequestException("Invalid credentials") )
} catch (t: Throwable) {
    throw BadRequestException("Invalid credentials")
}