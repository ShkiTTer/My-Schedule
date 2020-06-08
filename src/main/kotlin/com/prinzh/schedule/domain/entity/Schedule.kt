package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Schedule(
    val id: UUID,
    val group: Group,
    val discipline: TeacherDiscipline,
    val audience: Audience,
    val type: LessonType,
    val day: Int,
    val lessonNumber: Int,
    val weekStart: Int,
    val weekEnd: Int
) : IDataEntity