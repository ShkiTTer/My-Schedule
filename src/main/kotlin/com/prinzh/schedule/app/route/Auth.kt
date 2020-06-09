package com.prinzh.schedule.app.route

import com.prinzh.schedule.app.common.UserPrincipal
import com.prinzh.schedule.app.common.exception.UnauthorizedException
import com.prinzh.schedule.app.requests.LoginRequest
import com.prinzh.schedule.app.requests.TokenRequest
import com.prinzh.schedule.app.responses.common.DataResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.services.interfaces.IAuthService
import com.prinzh.schedule.domain.entity.UserRole
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Route.auth() {
    val service by inject<IAuthService>()

    route("auth") {
        post("login") {
            val data = call.receive<LoginRequest>()

            call.respond(DataResponse(ResponseInfo.OK, service.login(data)))
        }

        authenticate(UserRole.DEANERY.name) {
            post("token") {
                val data = call.receive<TokenRequest>()
                val userId = call.principal<UserPrincipal>()?.id ?: throw UnauthorizedException()

                call.respond(DataResponse(ResponseInfo.OK, service.updateToken(userId, data)))
            }
        }
    }
}