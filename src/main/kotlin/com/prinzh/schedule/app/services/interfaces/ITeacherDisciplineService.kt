package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.TeacherDisciplineRequest
import com.prinzh.schedule.app.responses.TeacherDisciplineResponse
import com.prinzh.schedule.domain.entity.TeacherDiscipline
import java.util.*

interface ITeacherDisciplineService {
    suspend fun getAll(): List<TeacherDisciplineResponse>
    suspend fun create(data: TeacherDisciplineRequest): List<TeacherDisciplineResponse>
    suspend fun update(data: TeacherDisciplineRequest): List<TeacherDisciplineResponse>
    suspend fun delete(teacherId: UUID)
}