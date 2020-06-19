package com.prinzh.schedule.app.route

import com.prinzh.schedule.app.responses.common.DataResponse
import com.prinzh.schedule.app.responses.common.ResponseInfo
import com.prinzh.schedule.app.services.interfaces.IRoleService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Route.role() {
    val service by inject<IRoleService>()

    route("roles") {
        get {
            call.respond(DataResponse(ResponseInfo.OK, service.getAll()))
        }
    }
}
