package com.prinzh.schedule.domain.entity

import java.util.*

data class User(
    val id: UUID? = null,
    val login: String,
    val password: String,
    val mail: String,
    val roles: List<Role>
)