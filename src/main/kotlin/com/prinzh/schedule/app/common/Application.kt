package com.prinzh.schedule.app.common

import com.google.gson.GsonBuilder
import com.prinzh.schedule.app.common.exception.ForbiddenException
import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.common.extension.toUserRole
import com.prinzh.schedule.app.common.util.JWTUtil
import com.prinzh.schedule.app.di.repositoryModule
import com.prinzh.schedule.app.di.serviceModule
import com.prinzh.schedule.app.responses.common.EmptyResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.route.api
import com.prinzh.schedule.app.services.interfaces.IUserService
import com.prinzh.schedule.data.db.common.DatabaseFactory
import com.prinzh.schedule.domain.entity.UserRole
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.ForbiddenResponse
import io.ktor.auth.jwt.jwt
import io.ktor.features.*
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject
import org.koin.ktor.ext.installKoin
import org.slf4j.event.Level
import java.text.DateFormat

@KtorExperimentalAPI
suspend fun main(args: Array<String>) {
    val port = System.getenv("PORT")?.toInt() ?: 55555

    embeddedServer(Netty, port) {
        DatabaseFactory.init()

        installKoin(listOf(repositoryModule, serviceModule))

        install(CallLogging) {
            level = Level.INFO
            filter { call -> call.request.path().startsWith("/") }
        }

        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)

            header(HttpHeaders.ContentType)
            header(HttpHeaders.Authorization)
            header(HttpHeaders.AccessControlAllowOrigin)

            allowCredentials = true
            anyHost()
        }

        install(DefaultHeaders) {
            header("X-Engine", "Ktor")
        }

        install(Authentication) {
            jwt(UserRole.DEANERY.name) {
                verifier(JWTUtil.verifier)

                validate {
                    val userService by inject<IUserService>()

                    val userId = it.payload.claims["id"]?.asString().toUUID()
                    val userRoles = it.payload.claims["roles"]
                        ?.asList(String::class.java)
                        ?.map { s -> s.toUUID() }
                        ?: throw ForbiddenException()

                    UserCredentials(userId, userRoles)
                }
            }
        }

        install(ContentNegotiation) {
            register(ContentType.Application.Json, GsonConverter(GsonBuilder().apply {
                setDateFormat(DateFormat.MEDIUM, DateFormat.MEDIUM)
                setPrettyPrinting()
            }.create()))
        }

        install(StatusPages) {
            exception<BadRequestException> {
                call.respond(EmptyResponse(ResponseInfo.BAD_REQUEST))
            }

            exception<NotFoundException> {
                call.respond(EmptyResponse(ResponseInfo.NOT_FOUND))
            }

            exception<ForbiddenException> {
                call.respond(EmptyResponse(ResponseInfo.FORBIDDEN))
            }
        }

        routing {
            get("/") {
                call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
            }

            api()
        }
    }.start(wait = true)
}