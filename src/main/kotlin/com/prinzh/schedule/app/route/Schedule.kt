package com.prinzh.schedule.app.route

import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.requests.ScheduleRequest
import com.prinzh.schedule.app.responses.common.DataResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.services.interfaces.IScheduleService
import io.ktor.application.call
import io.ktor.features.BadRequestException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Route.schedule() {
    val service by inject<IScheduleService>()

    route("schedules") {
        get {
            val teacherParam = call.request.queryParameters["teacher"]
            val weekParam = call.request.queryParameters["week"]

            if (weekParam != null) {
                teacherParam ?: throw BadRequestException("Invalid credentials")

                call.respond(
                    DataResponse(
                        ResponseInfo.OK,
                        service.getByTeacher(
                            teacherParam.toUUID(),
                            weekParam.toIntOrNull() ?: throw BadRequestException("Invalid credentials")
                        )
                    )
                )
            } else {
                call.respond(DataResponse(ResponseInfo.OK, service.getAll()))
            }
        }

        post {
            val data = call.receive<ScheduleRequest>()

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.create(data)
                )
            )
        }

        route("{id}") {
            get {
                val id = call.parameters["id"]

                call.respond(DataResponse(ResponseInfo.OK, service.getById(id.toUUID())))
            }

            put {
                val id = call.parameters["id"]
                val data = call.receive<ScheduleRequest>()

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
}