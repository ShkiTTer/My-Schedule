package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Audience
import java.util.*

data class FullAudienceResponse(
    val id: UUID,
    val audienceNumber: String,
    val building: BuildingResponse
) : IResponseContent {
    companion object : IResponseConverter<Audience, FullAudienceResponse> {
        override fun fromDomain(data: Audience): FullAudienceResponse = FullAudienceResponse(
            data.id,
            data.audienceNumber,
            BuildingResponse.fromDomain(data.building)
        )
    }
}