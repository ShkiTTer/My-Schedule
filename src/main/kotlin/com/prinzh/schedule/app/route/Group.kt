package com.prinzh.schedule.app.route

import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.requests.GroupRequest
import com.prinzh.schedule.app.responses.common.DataResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.services.interfaces.IGroupService
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Route.group() {
    val service: IGroupService by inject()

    route("group") {
        get {
            call.respond(DataResponse(ResponseInfo.OK, service.getAll()))
        }

        get("{id}") {
            val id = call.parameters["id"]

            call.respond(DataResponse(ResponseInfo.OK, service.getById(id.toUUID())))
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
            val id = call.parameters["id"]
            val data = call.receive<GroupRequest>()

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.update(id.toUUID(), data)
                )
            )
        }

        delete {
            val id = call.parameters["id"]

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.delete(id.toUUID())
                )
            )
        }
    }
}