package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Audience
import java.util.*

data class ShortAudienceResponse(
    val id: UUID,
    val audienceNumber: String
) : IResponseContent {
    companion object : IResponseConverter<Audience, ShortAudienceResponse> {
        override fun fromDomain(data: Audience): ShortAudienceResponse = ShortAudienceResponse(
            data.id,
            data.audienceNumber
        )
    }
}