package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Audience(
    val id: UUID? = null,
    val audienceNumber: String,
    val building: Building
): IDataEntity