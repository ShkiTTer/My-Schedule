package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Faculty(
    val id: UUID? = null,
    val title: String
): IDataEntity