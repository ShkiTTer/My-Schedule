package com.prinzh.schedule.domain.entity

import java.util.*

data class NewUser(
    val login: String,
    val password: String,
    val mail: String,
    val roles: List<UUID>
)