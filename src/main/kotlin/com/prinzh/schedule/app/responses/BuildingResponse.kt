package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

data class BuildingResponse(
    val id: UUID,
    val title: String,
    val address: String
): IResponseContent