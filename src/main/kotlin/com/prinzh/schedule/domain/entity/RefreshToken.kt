package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class RefreshToken(
    val id: UUID,
    val token: String,
    val expired: Long
) : IDataEntity