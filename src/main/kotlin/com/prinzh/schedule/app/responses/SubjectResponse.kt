package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Subject
import java.util.*

data class SubjectResponse(
    val id: UUID,
    val title: String
) : IResponseContent {
    companion object : IResponseConverter<Subject, SubjectResponse> {
        override fun fromDomain(data: Subject): SubjectResponse = SubjectResponse(
            data.id,
            data.title
        )
    }
}