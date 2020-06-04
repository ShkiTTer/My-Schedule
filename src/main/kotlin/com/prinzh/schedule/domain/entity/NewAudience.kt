package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity
import java.util.*

data class NewAudience(
    val audienceNumber: String,
    val buildingId: UUID
): INewEntity