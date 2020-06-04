package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.LessonType
import com.prinzh.schedule.domain.entity.NewLessonType
import java.util.*

interface ILessonTypeRepository: ICrudRepository<NewLessonType, LessonType, UUID> {
}