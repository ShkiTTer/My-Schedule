package com.prinzh.schedule.app.common.extension

import io.ktor.features.BadRequestException
import io.ktor.util.KtorExperimentalAPI
import java.util.*

@KtorExperimentalAPI
fun String.toUUID(): UUID = try {
    UUID.fromString(this)
} catch (t: Throwable) {
    throw BadRequestException("Invalid credentials")
}