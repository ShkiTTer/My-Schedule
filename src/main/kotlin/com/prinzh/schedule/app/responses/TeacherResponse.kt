package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Teacher
import java.util.*

data class TeacherResponse(
    val id: UUID,
    val surname: String,
    val name: String,
    val patronymic: String
) : IResponseContent {
    companion object : IResponseConverter<Teacher, TeacherResponse> {
        override fun fromDomain(data: Teacher): TeacherResponse = TeacherResponse(
            data.id!!,
            data.surname,
            data.name,
            data.patronymic
        )
    }
}