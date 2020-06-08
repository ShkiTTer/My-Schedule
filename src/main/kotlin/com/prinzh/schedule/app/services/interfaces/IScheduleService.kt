package com.prinzh.schedule.app.services.interfaces

import com.prinzh.schedule.app.requests.ScheduleRequest
import java.util.*

interface IScheduleService: ICrudService<ScheduleRequest, UUID> {
}