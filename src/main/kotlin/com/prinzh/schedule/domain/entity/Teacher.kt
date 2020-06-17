package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class Teacher(
    val id: UUID,
    val surname: String,
    val name: String,
    val patronymic: String,
    val subjects: List<Subject>
): IDataEntity