package com.prinzh.schedule.domain.entity

import java.util.*

data class TeacherDiscipline(
    val id: UUID? = null,
    val teacher: Teacher,
    val subject: Subject,
    val lessonType: LessonType
)