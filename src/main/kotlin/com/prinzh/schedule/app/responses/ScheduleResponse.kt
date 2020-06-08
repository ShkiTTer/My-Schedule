package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Schedule
import java.util.*

data class ScheduleResponse(
    val id: UUID,
    val group: ShortGroupResponse,
    val discipline: TeacherDisciplineResponse,
    val audience: FullAudienceResponse,
    val lessonType: LessonTypeResponse,
    val day: Int,
    val lessonNumber: Int,
    val weekStart: Int,
    val weekEnd: Int
) : IResponseContent {
    companion object : IResponseConverter<Schedule, IResponseContent> {
        override fun fromDomain(data: Schedule): IResponseContent = ScheduleResponse(
            id = data.id,
            group = ShortGroupResponse.fromDomain(data.group),
            discipline = TeacherDisciplineResponse.fromDomain(data.discipline),
            audience = FullAudienceResponse.fromDomain(data.audience),
            lessonType = LessonTypeResponse.fromDomain(data.type),
            day = data.day,
            lessonNumber = data.lessonNumber,
            weekStart = data.weekStart,
            weekEnd = data.weekEnd
        )
    }
}