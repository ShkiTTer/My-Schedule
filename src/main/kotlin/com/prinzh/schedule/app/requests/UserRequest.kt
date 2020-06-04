package com.prinzh.schedule.app.requests

data class UserRequest(
    val login: String?,
    val password: String?,
    val mail: String?,
    val roles: List<String>?
)