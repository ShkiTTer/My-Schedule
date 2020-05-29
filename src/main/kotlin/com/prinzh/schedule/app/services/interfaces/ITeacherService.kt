package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.TeacherRequest
import com.prinzh.schedule.app.services.interfaces.ICrudService
import com.prinzh.schedule.domain.entity.Teacher
import java.util.*

interface ITeacherService: ICrudService<TeacherRequest, Teacher, UUID> {
}