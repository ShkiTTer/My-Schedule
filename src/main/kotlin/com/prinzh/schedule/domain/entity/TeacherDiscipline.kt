package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.IDataEntity
import java.util.*

data class TeacherDiscipline(
    val id: UUID,
    val teacher: Teacher,
    val subject: Subject
): IDataEntity