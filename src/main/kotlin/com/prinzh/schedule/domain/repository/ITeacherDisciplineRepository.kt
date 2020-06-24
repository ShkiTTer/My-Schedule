package com.prinzh.schedule.domain.repository

import com.prinzh.schedule.domain.entity.NewTeacherDiscipline
import com.prinzh.schedule.domain.entity.TeacherDiscipline
import java.util.*

interface ITeacherDisciplineRepository: ICrudRepository<NewTeacherDiscipline, TeacherDiscipline, UUID> {
    suspend fun getByTeacher(teacherId: UUID): List<TeacherDiscipline>
}