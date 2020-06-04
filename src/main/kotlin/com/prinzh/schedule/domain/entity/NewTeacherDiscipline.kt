package com.prinzh.schedule.domain.entity

import com.prinzh.schedule.domain.entity.common.INewEntity
import java.util.*

data class NewTeacherDiscipline(
    val teacherId: UUID,
    val subjectId: UUID,
    val typeId: UUID
): INewEntity