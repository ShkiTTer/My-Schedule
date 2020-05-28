package com.prinzh.schedule.app.route

import io.ktor.routing.Route
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun Route.api() {
    route("api") {
        faculty()
        building()
        subject()
    }
}