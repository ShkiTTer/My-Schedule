package com.prinzh.schedule.app.responses

import com.prinzh.schedule.app.responses.common.IResponseContent
import com.prinzh.schedule.app.responses.common.IResponseConverter
import com.prinzh.schedule.domain.entity.Faculty
import com.prinzh.schedule.domain.entity.LessonType
import java.util.*

data class LessonTypeResponse(
    val id: UUID,
    val type: String
) : IResponseContent {
    companion object : IResponseConverter<LessonType, LessonTypeResponse> {
        override fun fromDomain(data: LessonType): LessonTypeResponse = LessonTypeResponse(
            data.id!!,
            data.type
        )
    }
}