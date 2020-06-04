package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

data class LessonTypeResponse(
    val id: UUID,
    val type: String
) : IResponseContent