package com.prinzh.schedule.app.route

import com.prinzh.schedule.app.requests.TeacherRequest
import com.prinzh.schedule.app.responses.common.DataResponse
import com.prinzh.schedule.app.responses.common.EmptyResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.services.interfaces.ITeacherService
import io.ktor.application.call
import io.ktor.features.BadRequestException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject
import java.util.*

@KtorExperimentalAPI
fun Route.teacher() {
    val service: ITeacherService by inject()

    route("teacher") {
        get {
            val queryParam = call.request.queryParameters["id"]

            if (queryParam.isNullOrEmpty()) {
                call.respond(DataResponse(ResponseInfo.OK, service.getAll()))
            } else {
                val id = try {
                    UUID.fromString(queryParam)
                } catch (e: Exception) {
                    throw BadRequestException("Invalid credentials")
                }

                call.respond(DataResponse(ResponseInfo.OK, service.getById(id)))
            }
        }

        get("search") {
            val query = call.request.queryParameters["query"]

            call.respond(DataResponse(ResponseInfo.OK, service.search(query)))
        }

        post {
            val data = call.receive<TeacherRequest>()

            call.respond(
                    DataResponse(
                            ResponseInfo.OK,
                            service.create(data)
                    )
            )
        }

        put {
            val id = try {
                UUID.fromString(call.request.queryParameters["id"])
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            } ?: throw BadRequestException("Invalid credentials")

            val data = call.receive<TeacherRequest>()

            call.respond(
                    DataResponse(
                            ResponseInfo.OK,
                            service.update(id, data)
                    )
            )
        }

        delete {
            val id = try {
                UUID.fromString(call.request.queryParameters["id"])
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            } ?: throw BadRequestException("Invalid credentials")

            service.delete(id)
            call.respond(EmptyResponse(ResponseInfo.OK))
        }
    }
}