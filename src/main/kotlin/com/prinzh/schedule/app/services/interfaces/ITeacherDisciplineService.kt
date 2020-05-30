package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.TeacherDisciplineRequest
import com.prinzh.schedule.domain.entity.TeacherDiscipline
import java.util.*

interface ITeacherDisciplineService: ICrudService<TeacherDisciplineRequest, TeacherDiscipline, UUID> {
}