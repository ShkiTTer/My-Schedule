package com.prinzh.schedule.app.route

import com.prinzh.schedule.app.requests.GroupRequest
import com.prinzh.schedule.app.responses.common.DataResponse
import com.prinzh.schedule.app.responses.common.EmptyResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.services.interfaces.IGroupService
import io.ktor.application.call
import io.ktor.features.BadRequestException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject
import java.util.*

@KtorExperimentalAPI
fun Route.group() {
    val service: IGroupService by inject()

    route("group") {
        get {
            call.respond(DataResponse(ResponseInfo.OK, service.getAll()))
        }

        get("{id}") {
            val id = try {
                UUID.fromString(call.parameters["id"])
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            } ?: throw BadRequestException("Invalid credentials")

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.getById(id)
                )
            )
        }

        post {
            val data = call.receive<GroupRequest>()

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.create(data)
                )
            )
        }

        put {
            val id = try {
                UUID.fromString(call.parameters["id"])
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            } ?: throw BadRequestException("Invalid credentials")

            val data = call.receive<GroupRequest>()

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.update(id, data)
                )
            )
        }

        delete {
            val id = try {
                UUID.fromString(call.parameters["id"])
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            } ?: throw BadRequestException("Invalid credentials")

            service.delete(id)
            call.respond(EmptyResponse(ResponseInfo.OK))
        }
    }
}