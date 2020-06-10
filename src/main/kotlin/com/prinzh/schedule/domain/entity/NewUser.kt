package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity
import java.util.*

data class NewUser(
    val login: String,
    val password: String,
    val mail: String,
    val salt: ByteArray,
    val role: UUID
) : INewEntity