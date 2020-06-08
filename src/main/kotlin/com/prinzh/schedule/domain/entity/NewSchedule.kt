package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity
import java.util.*

data class NewSchedule(
    val groupId: UUID,
    val disciplineId: UUID,
    val audienceId: UUID,
    val typeId: UUID,
    val day: Int,
    val lessonNumber: Int,
    val weekStart: Int,
    val weekEnd: Int
) : INewEntity