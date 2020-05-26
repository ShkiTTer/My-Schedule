package com.prinzh.schedule.app.common

import com.prinzh.schedule.app.di.serviceModule
import com.prinzh.schedule.data.db.common.DatabaseFactory
import com.prinzh.schedule.domain.common.response.ErrorResponse
import com.prinzh.schedule.domain.common.response.Response
import com.prinzh.schedule.domain.common.response.ResponseInfo
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.ForbiddenResponse
import io.ktor.client.statement.HttpResponse
import io.ktor.features.*
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.content.OutgoingContent
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.installKoin
import org.slf4j.event.Level
import java.text.DateFormat

@KtorExperimentalAPI
fun main(args: Array<String>) {
    val port = System.getenv("PORT")?.toInt() ?: 23888

    embeddedServer(Netty, port) {
        DatabaseFactory.init()

        installKoin(listOf(serviceModule))

        install(CallLogging) {
            level = Level.INFO
            filter { call -> call.request.path().startsWith("/") }
        }

        install(DefaultHeaders) {
            header("X-Engine", "Ktor") // will send this header with each response
        }

        install(ContentNegotiation) {
            gson {
                setDateFormat(DateFormat.MEDIUM, DateFormat.MEDIUM)
            }
        }

        install(StatusPages) {
            exception<BadRequestException> {
                call.respond(ErrorResponse(ResponseInfo.BAD_REQUEST))
            }
            exception<NotFoundException> {
                call.respond(ErrorResponse(ResponseInfo.NOT_FOUND))
            }
        }

        routing {
            get("/") {
                call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
            }
        }
    }.start(wait = true)
}