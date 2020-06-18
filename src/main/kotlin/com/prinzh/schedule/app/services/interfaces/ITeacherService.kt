package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.TeacherRequest
import com.prinzh.schedule.app.responses.TeacherResponse
import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

interface ITeacherService : ICrudService<TeacherRequest, UUID>, ISearchService {
    suspend fun getBySubject(subjectId: UUID): List<IResponseContent>
}