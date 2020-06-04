package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.NewTeacher
import com.prinzh.schedule.domain.entity.Teacher
import java.util.*

interface ITeacherRepository: ICrudRepository<NewTeacher, Teacher, UUID> {
}