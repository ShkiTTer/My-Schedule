package com.prinzh.schedule.app.common

import com.google.gson.GsonBuilder
import com.prinzh.schedule.app.common.util.HashUtil
import com.prinzh.schedule.app.di.repositoryModule
import com.prinzh.schedule.app.di.serviceModule
import com.prinzh.schedule.app.responses.common.EmptyResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.route.api
import com.prinzh.schedule.data.db.common.DatabaseFactory
import com.prinzh.schedule.data.db.entity.UserEntity
import io.ktor.application.call
import io.ktor.application.install
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.ktor.ext.installKoin
import org.slf4j.event.Level
import java.nio.charset.Charset
import java.text.DateFormat
import java.util.*
import javax.sql.rowset.serial.SerialBlob

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
        }

        routing {
            get("/") {
                call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
            }

            api()
        }
    }.start(wait = true)
}