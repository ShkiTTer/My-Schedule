package com.prinzh.schedule.domain.entity

import java.util.*

data class NewTeacherDiscipline(
    val teacherId: UUID,
    val subjectId: UUID,
    val typeId: UUID
)