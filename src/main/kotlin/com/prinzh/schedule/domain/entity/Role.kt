package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Role(
    val id: UUID? = null,
    val role: UserRole
): IDataEntity