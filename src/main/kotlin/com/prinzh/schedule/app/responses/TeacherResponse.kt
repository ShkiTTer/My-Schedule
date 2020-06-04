package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

data class TeacherResponse(
    val id: UUID,
    val surname: String,
    val name: String,
    val patronymic: String
) : IResponseContent