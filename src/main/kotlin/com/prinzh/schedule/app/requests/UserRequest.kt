package com.prinzh.schedule.app.requests

import com.prinzh.schedule.domain.entity.Role

data class UserRequest(
    val login: String,
    val password: String,
    val mail: String,
    val roles: List<String>
)