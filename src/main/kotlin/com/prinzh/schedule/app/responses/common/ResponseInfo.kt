package com.prinzh.schedule.app.responses.common

import io.ktor.http.HttpStatusCode
import java.util.*

data class ResponseInfo(
    val status: String,
    val statusCode: Int,
    val timestamp: Long = Date().time
) {
    companion object {
        val OK = ResponseInfo(
            HttpStatusCode.OK.description,
            HttpStatusCode.OK.value
        )

        val BAD_REQUEST = ResponseInfo(
            HttpStatusCode.BadRequest.description,
            HttpStatusCode.BadRequest.value
        )

        val NOT_FOUND = ResponseInfo(
            HttpStatusCode.NotFound.description,
            HttpStatusCode.NotFound.value
        )

        val UNAUTHORIZED = ResponseInfo(
            HttpStatusCode.Unauthorized.description,
            HttpStatusCode.Unauthorized.value
        )

        val FORBIDDEN = ResponseInfo(
            HttpStatusCode.Forbidden.description,
            HttpStatusCode.Forbidden.value
        )
    }
}