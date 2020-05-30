package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.LessonTypeRequest
import com.prinzh.schedule.domain.entity.LessonType
import java.util.*

interface ILessonTypeService: ICrudService<LessonTypeRequest, LessonType, UUID> {
}