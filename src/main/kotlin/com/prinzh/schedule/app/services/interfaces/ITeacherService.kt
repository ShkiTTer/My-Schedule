package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.TeacherRequest
import com.prinzh.schedule.app.responses.TeacherResponse
import java.util.*

interface ITeacherService : ICrudService<TeacherRequest, UUID> {
    suspend fun search(query: String?): List<TeacherResponse>
    suspend fun getBySubject(subjectId: UUID): List<TeacherResponse>
}