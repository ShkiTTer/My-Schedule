package com.prinzh.schedule.app.route

import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.requests.SubjectRequest
import com.prinzh.schedule.app.responses.common.DataResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.services.interfaces.ISubjectService
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Route.subject() {
    val service: ISubjectService by inject()

    route("subject") {
        get {
            val teacherParam = call.request.queryParameters["teacher"]

            if (teacherParam != null) {
                call.respond(DataResponse(ResponseInfo.OK, service.getByTeacher(teacherParam.toUUID())))
            } else {
                call.respond(DataResponse(ResponseInfo.OK, service.getAll()))
            }
        }

        get("{id}") {
            val id = call.parameters["id"]

            call.respond(DataResponse(ResponseInfo.OK, service.getById(id.toUUID())))
        }

        post {
            val data = call.receive<SubjectRequest>()

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.create(data)
                )
            )
        }

        put {
            val id = call.parameters["id"]
            val data = call.receive<SubjectRequest>()

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