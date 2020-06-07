package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity

data class NewLessonType(
    val type: String,
    val color: String
): INewEntity