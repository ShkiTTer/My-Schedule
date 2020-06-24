package com.prinzh.schedule.app.route

import com.prinzh.schedule.app.common.extension.toUUID
import com.prinzh.schedule.app.requests.TeacherDisciplineRequest
import com.prinzh.schedule.app.responses.common.DataResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.services.interfaces.ITeacherDisciplineService
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Route.teacherDiscipline() {
    val service: ITeacherDisciplineService by inject()

    route("disciplines") {
        get {
            call.respond(DataResponse(ResponseInfo.OK, service.getAll()))
        }

        post {
            val data = call.receive<TeacherDisciplineRequest>()

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.create(data)
                )
            )
        }

        put {
            val data = call.receive<TeacherDisciplineRequest>()

            call.respond(
                DataResponse(
                    ResponseInfo.OK,
                    service.update(data)
                )
            )
        }

        route("{id}") {
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