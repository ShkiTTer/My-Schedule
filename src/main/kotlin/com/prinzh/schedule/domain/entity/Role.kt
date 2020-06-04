package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Role(
    val id: UUID,
    val role: UserRole
): IDataEntity