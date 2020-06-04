package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

data class FullAudienceResponse(
    val id: UUID,
    val audienceNumber: String,
    val building: BuildingResponse
) : IResponseContent