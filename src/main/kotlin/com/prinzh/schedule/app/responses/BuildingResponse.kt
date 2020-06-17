package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Building
import com.prinzh.schedule.domain.entity.Faculty
import java.util.*

data class BuildingResponse(
    val id: UUID,
    val title: String,
    val address: String
) : IResponseContent {
    companion object : IResponseConverter<Building, BuildingResponse> {
        override fun fromDomain(data: Building): BuildingResponse = BuildingResponse(
            data.id,
            data.title,
            data.address
        )
    }
}