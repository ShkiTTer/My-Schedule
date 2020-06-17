package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Faculty
import java.util.*

data class FacultyResponse(
    val id: UUID,
    val title: String
) : IResponseContent {
    companion object : IResponseConverter<Faculty, FacultyResponse> {
        override fun fromDomain(data: Faculty): FacultyResponse = FacultyResponse(
            data.id,
            data.title
        )
    }
}