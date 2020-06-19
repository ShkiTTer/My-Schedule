package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.ScheduleRequest
import com.prinzh.schedule.app.responses.common.IResponseContent
import java.util.*

interface IScheduleService : ICrudService<ScheduleRequest, UUID> {
    suspend fun getByTeacher(teacherId: UUID, week: Int): List<IResponseContent>
}