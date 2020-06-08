package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity
import java.util.*

data class NewRefreshToken(
    val token: String,
    val expired: Long,
    val userId: UUID
) : INewEntity