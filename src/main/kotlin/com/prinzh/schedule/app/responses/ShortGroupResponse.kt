package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

data class ShortGroupResponse(
    val id: UUID,
    val title: String,
    val faculty: FacultyResponse
) : IResponseContent