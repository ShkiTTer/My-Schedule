package com.prinzh.schedule.app.route

import com.prinzh.schedule.domain.common.response.DataResponse
import com.prinzh.schedule.domain.common.response.EmptyResponse
import com.prinzh.schedule.domain.common.response.ResponseInfo
import com.prinzh.schedule.domain.entity.Building
import com.prinzh.schedule.domain.entity.Faculty
import com.prinzh.schedule.domain.services.IBuildingService
import io.ktor.application.call
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject
import java.util.*

@KtorExperimentalAPI
fun Route.building() {
    val service: IBuildingService by inject()

    route("building") {
        get {
            call.respond(DataResponse(ResponseInfo.OK, service.getAll()))
        }

        get("{id}") {
            val id = try {
                UUID.fromString(call.parameters["id"])
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            } ?: throw BadRequestException("Invalid credentials")

            val building = service.getById(id) ?: throw NotFoundException()

            call.respond(DataResponse(ResponseInfo.OK, building))
        }

        post {
            val data = call.receive<Building>()

            if (data.address.isNullOrEmpty() || data.title.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

            call.respond(DataResponse(ResponseInfo.OK, service.create(data)))
        }

        put {
            val id = try {
                UUID.fromString(call.parameters["id"])
            } catch (e: Exception) {
                throw BadRequestException("Invalid credentials")
            } ?: throw BadRequestException("Invalid credentials")

            val data = call.receive<Building>()

            if (data.title.isNullOrEmpty() || data.address.isNullOrEmpty()) throw BadRequestException("Invalid credentials")

            call.respond(DataResponse(ResponseInfo.OK, service.update(id, data)))
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