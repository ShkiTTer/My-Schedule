package com.prinzh.schedule.app.common

import com.prinzh.schedule.app.di.repositoryModule
import com.prinzh.schedule.app.di.serviceModule
import com.prinzh.schedule.app.route.api
import com.prinzh.schedule.data.db.common.DatabaseFactory
import com.prinzh.schedule.app.responses.common.EmptyResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.gson.gson
import io.ktor.http.ContentType
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
    val port = System.getenv("PORT")?.toInt() ?: 55555

    embeddedServer(Netty, port) {
        DatabaseFactory.init()

        installKoin(listOf(repositoryModule, serviceModule))

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
                setPrettyPrinting()
            }
        }

        install(StatusPages) {
            exception<BadRequestException> {
                call.respond(EmptyResponse(ResponseInfo.BAD_REQUEST))
            }
            exception<NotFoundException> {
                call.respond(EmptyResponse(ResponseInfo.NOT_FOUND))
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