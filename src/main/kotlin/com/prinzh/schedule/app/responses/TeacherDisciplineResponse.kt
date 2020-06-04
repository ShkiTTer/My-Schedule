package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Teacher
import com.prinzh.schedule.domain.entity.TeacherDiscipline
import java.util.*

data class TeacherDisciplineResponse(
    val id: UUID,
    val teacher: TeacherResponse,
    val subject: SubjectResponse,
    val type: LessonTypeResponse
) : IResponseContent {
    companion object : IResponseConverter<TeacherDiscipline, TeacherDisciplineResponse> {
        override fun fromDomain(data: TeacherDiscipline): TeacherDisciplineResponse = TeacherDisciplineResponse(
            data.id!!,
            TeacherResponse.fromDomain(data.teacher),
            SubjectResponse.fromDomain(data.subject),
            LessonTypeResponse.fromDomain(data.lessonType)
        )
    }
}