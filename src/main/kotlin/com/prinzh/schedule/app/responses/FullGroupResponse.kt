package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Group
import java.util.*

data class FullGroupResponse(
    val id: UUID,
    val title: String,
    val faculty: FacultyResponse,
    val parent: ShortGroupResponse?
) : IResponseContent {
    companion object : IResponseConverter<Group, FullGroupResponse> {
        override fun fromDomain(data: Group): FullGroupResponse = FullGroupResponse(
            data.id,
            data.title,
            FacultyResponse.fromDomain(data.faculty),
            data.parentGroup?.let {
                ShortGroupResponse.fromDomain(it)
            }
        )
    }
}