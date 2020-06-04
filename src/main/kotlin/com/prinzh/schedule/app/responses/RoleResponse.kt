package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

data class RoleResponse(
    val id: UUID,
    val role: String
) : IResponseContent