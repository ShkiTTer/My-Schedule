package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class LessonType(
    val id: UUID,
    val type: String
): IDataEntity