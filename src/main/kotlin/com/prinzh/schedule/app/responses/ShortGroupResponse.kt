package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Group
import java.util.*

data class ShortGroupResponse(
    val id: UUID,
    val title: String,
    val faculty: FacultyResponse
) : IResponseContent {
    companion object : IResponseConverter<Group, ShortGroupResponse> {
        override fun fromDomain(data: Group): ShortGroupResponse = ShortGroupResponse(
            data.id,
            data.title,
            FacultyResponse.fromDomain(data.faculty)
        )
    }
}