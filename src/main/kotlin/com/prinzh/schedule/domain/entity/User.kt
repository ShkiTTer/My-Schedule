package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class User(
    val id: UUID,
    val login: String,
    val password: String,
    val mail: String,
    val salt: ByteArray,
    val roles: List<Role>
): IDataEntity