package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

data class ShortAudienceResponse(
    val id: UUID,
    val audienceNumber: String
) : IResponseContent