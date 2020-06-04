package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

data class TeacherDisciplineResponse(
    val id: UUID,
    val teacher: TeacherResponse,
    val subject: SubjectResponse,
    val type: LessonTypeResponse
) : IResponseContent